package com.auth.identity.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient<T,U> {
    final static  String URL = "http://localhost:8082/v1/api/";
    public ResponseEntity<U> postService(T t, String urlPath, Class<U> clazz) {
        return  new RestTemplate().postForEntity(URL+urlPath, t, clazz);
    }
}
