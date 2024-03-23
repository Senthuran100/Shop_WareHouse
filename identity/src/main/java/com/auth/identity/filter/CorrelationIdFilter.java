package com.auth.identity.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    private  String CorrelationIDHeader = "X-CorrelationID";
    public final static String CorrelationIdMDC = "CorrelationId";

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String correlationId = getCorrelationId();
            MDC.put(CorrelationIdMDC, correlationId);
            response.addHeader(CorrelationIDHeader, correlationId);
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("CorrelationId");
        }
    }
}
