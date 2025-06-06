package com.harsh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter  implements GatewayFilter{

	 	@Autowired
	    private RouterValidator routerValidator;//custom route validator
	    @Autowired
	    private JwtUtil jwtUtil;
		
	    @Override
		public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
			// TODO Auto-generated method stub
	    	ServerHttpRequest request = exchange.getRequest();

	        if (routerValidator.isSecured.test(request)) {
	            if (this.isAuthMissing(request))
	                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

	            final String token = this.getAuthHeader(request);

	            if (jwtUtil.isInvalid(token))
	                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

	            this.populateRequestWithHeaders(exchange, token);
	        }
	        return chain.filter(exchange);
		}
	    
	    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
	        ServerHttpResponse response = exchange.getResponse();
	        response.setStatusCode(httpStatus);
	 
	        return response.setComplete();
	    }

	    private String getAuthHeader(ServerHttpRequest request) {
	        return request.getHeaders().getOrEmpty("Authorization").get(0);
	    }

	    private boolean isAuthMissing(ServerHttpRequest request) {
	        return !request.getHeaders().containsKey("Authorization");
	    }

	    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
	       
	        exchange.getRequest().mutate()
	                .build();
	    }
	    
	    
	    
	    
}
