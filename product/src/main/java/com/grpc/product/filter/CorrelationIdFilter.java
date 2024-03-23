package com.grpc.product.filter;


import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    private final String correlationIdHeader = "X-Correlation-Identity";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String correlationId = request.getHeader(correlationIdHeader);
            if (null != correlationId && !correlationId.isBlank()) {
                MDC.put("CorrelationId", correlationId);
            }
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("CorrelationId");
        }
    }


}
