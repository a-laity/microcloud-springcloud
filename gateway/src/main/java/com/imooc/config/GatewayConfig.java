package com.imooc.config;

import com.alibaba.nacos.api.PropertyKeyConst;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@Getter//get方法
@Setter//set方法
public class GatewayConfig {
    /**
     * 读取配置的超时时间
     */
    public static final long DEFAULT_TIMEOUT = 30000;
    /**
     * Nacos 服务器地址
     */
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    public String nacosServerAddr;
    /**
     * Nacos 命名空间
     */
    @Value("${spring.cloud.nacos.discovery.namespace}")
    public String nacosNamespace;
    /**
     * nacos 配置列表中的dataid
     */
    @Value("${nacos.gateway.route.config.data-id}")
    public String nacosRouteDateId;
    /**
     * nacos分组id
     */
    @Value("${nacos.gateway.route.config.group}")
    public String nacosGroupId;

    public Properties getNacosProperties() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, this.nacosServerAddr);
        properties.put(PropertyKeyConst.NAMESPACE, this.nacosNamespace);
        return properties;
    }
}
