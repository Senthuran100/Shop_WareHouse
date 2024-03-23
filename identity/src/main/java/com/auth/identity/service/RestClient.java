package com.auth.identity.service;

import com.auth.identity.exception.CustomGraphQLException;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.auth.identity.filter.CorrelationIdFilter.CorrelationIdMDC;

@Service
public class RestClient<T,U> {

    private String correlationIdHeader = "X-Correlation-Identity";

    @Value("${app.product.serviceURL}")
    private String URL;

    public ResponseEntity<U> postService(T t, String urlPath, Class<U> clazz) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set(correlationIdHeader, MDC.get(CorrelationIdMDC));
            HttpEntity<T> httpEntity = new HttpEntity<>(t, headers);

            return new RestTemplate().postForEntity(URL + urlPath, httpEntity, clazz);
        } catch (HttpClientErrorException exception) {
            throw new CustomGraphQLException(exception.getStatusCode().value(),exception.getMessage());
        } catch (Exception exception) {
            throw new CustomGraphQLException(500, exception.getMessage());
        }
    }
}
