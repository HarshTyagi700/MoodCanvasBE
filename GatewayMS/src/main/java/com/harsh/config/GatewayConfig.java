package com.harsh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("UserMS", r -> r.path("/pinterest/user-api/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://UserMS"))

                .route("PinsMS", r -> r.path("/pinterest/pins-api/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PinsMS"))
                
                .route("PinBoardMS", r -> r.path("/pinterest/pinboard-api/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PinBoardMS"))
                
                .route("AnalyticsMS", r -> r.path("/pinterest/pins-api/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PinsMS"))
                
 
                .build();
    }

}
