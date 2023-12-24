package com.auth.identity.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient<T> {
    final static  String url = "http://localhost:8080/v1/api/";
    public ResponseEntity<T> postService(T t, String urlPath, Class<T> clazz) {
        return  new RestTemplate().postForEntity(url+urlPath, t, clazz);
    }
}
