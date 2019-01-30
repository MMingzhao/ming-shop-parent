package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Mingzhao
 * @date 2019/1/28 11:22
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableZuulProxy
//@EnableDiscoveryClient
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
