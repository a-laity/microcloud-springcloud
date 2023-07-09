package com.imooc.listener;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;

import com.imooc.config.GatewayConfig;
import com.imooc.service.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@Slf4j
public class GatewayNacosRouteListener implements CommandLineRunner {
    @Autowired
    private DynamicRouteService dynamicRouteService; // 设置业务层处理
    @Autowired
    private GatewayConfig gatewayConfig; // Nacos服务配置


    @Override
    public void run(String... args) throws Exception {
        this.nacosDynmaicRouteListener();
    }

    public void nacosDynmaicRouteListener() {   // 动态路由监听
        try {
            ConfigService configService = NacosFactory.createConfigService(this.gatewayConfig.getNacosProperties());
            String content = configService.getConfig(this.gatewayConfig.getNacosRouteDateId(), this.gatewayConfig.getNacosGroupId(), this.gatewayConfig.DEFAULT_TIMEOUT); // 获取指定的配置项
            log.info("【网关启动】读取Nacos网关配置项：{}", content); // 日志输出
            GatewayNacosRouteListener.this.setRoute(content); // 路由配置
            configService.addListener(this.gatewayConfig.getNacosRouteDateId(), this.gatewayConfig.getNacosGroupId(), new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("【网关更新】读取Nacos网关配置项：{}", configInfo); // 日志输出
                    GatewayNacosRouteListener.this.setRoute(configInfo);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setRoute(String configInfo) { // 定义路由处理
        try {   // 将读取到的数据内容转为路由的配置定义，本操作是由Jackson组件完成的
            //RouteDefinition[] routes = this.mapper.readValue(configInfo, RouteDefinition[].class);
            RouteDefinition[] routes = JSON.parseObject(configInfo, RouteDefinition[].class);
            for (RouteDefinition route : routes) {
                this.dynamicRouteService.update(route); // 业务更新
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
