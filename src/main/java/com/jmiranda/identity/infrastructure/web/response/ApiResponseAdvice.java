package com.jmiranda.identity.infrastructure.web.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true; // aplica a todos los controllers
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {

        // Ya es ApiResponse → no envolver de nuevo
        if (body instanceof ApiResponse<?>) {
            return body;
        }

        // No body (204, etc.)
        if (body == null) {
            return new ApiResponse<>(true, null, null);
        }

        return new ApiResponse<>(true, body, null);
    }
}
