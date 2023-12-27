package com.auth.identity.service;

import com.auth.identity.exception.CustomGraphQLException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class RestClient<T,U> {
    // TODO: Move the below variable to the application.properties
    final static  String URL = "http://localhost:8082/v1/api/";

    public ResponseEntity<U> postService(T t, String urlPath, Class<U> clazz) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<T> httpEntity = new HttpEntity<>(t, headers);

            return new RestTemplate().postForEntity(URL + urlPath, httpEntity, clazz);
        } catch (HttpClientErrorException exception) {
            throw new CustomGraphQLException(exception.getStatusCode().value(),exception.getMessage());
        } catch (Exception exception) {
            throw new CustomGraphQLException(500, exception.getMessage());
        }
    }
}
