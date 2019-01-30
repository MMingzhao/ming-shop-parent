package com.ming.config;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mingzhao
 * @date 2019/1/28 16:48
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider{

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
//        List<String> services = discoveryClient.getServices();
//        services.forEach(x -> {
//            resources.add(swaggerResource(x,
//                    "/v2/api-docs", "2.0"));
//        });

        // 网关使用服务别名获取远程服务的SwaggerApi
        resources.add(swaggerResource("ming-shop-service-wechat",
                "/ming-shop-service-wechat/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
