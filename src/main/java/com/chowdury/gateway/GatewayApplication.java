package com.chowdury.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {


    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);

    }


}

@Configuration
class BeanConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route(r -> r.path("/**")
//                        .filters(f -> f.hystrix(h -> h.setName("Hystrix")
//                                .setFallbackUri("forward:/fallback/message")))
//                        .uri("lb:http://user-service")
//                        .id("user-service"))
//                .build();
                .route(r -> r.path("/user-service/**")
                        .uri("lb:http://USER-SERVICE/")
                        .id("user-service")
                )
                .route(r->r
                        .path("/api/student")
                        .uri("lb:https://registration")
                        .id("registration")
                )
                .build();

    }

}