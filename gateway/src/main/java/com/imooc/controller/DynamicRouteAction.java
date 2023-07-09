package com.imooc.controller;

import com.imooc.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/routes/*") // 访问父路径
public class DynamicRouteAction {
    @Autowired
    private DynamicRouteService dynamicRouteService; // 路由业务对象

    @PostMapping("add")
    public Boolean add(@RequestBody RouteDefinition definition) {
        return this.dynamicRouteService.add(definition);
    }

    @DeleteMapping("delete/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id);
    }

    @PostMapping("update")
    public Boolean update(@RequestBody RouteDefinition definition) {
        return this.dynamicRouteService.update(definition);
    }
}
