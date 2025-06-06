package com.harsh.config;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouterValidator {

	public static final List<String> openApiEndpoints = List.of(
            "/pinterest/user-api/register",
            "/pinterest/user-api/login",
            "/pinterest/pins-api/all",
            "/pinterest/pins-api/search",
            "/pinterest/pins-api/pin",
            "/pinterest/pins-api/reindex"
            
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().startsWith(uri));
}
