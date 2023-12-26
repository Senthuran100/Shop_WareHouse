package com.auth.identity.controller;

import com.auth.identity.payload.response.CategoryResponse;
import com.auth.identity.service.impl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class GraphqlMutationController {

    private final CategoryServiceImpl categoryService;

    @MutationMapping
    public CategoryResponse createCategory(@Argument String name) {
        return categoryService.invokeCreateCategory(name).getBody();
    }

}
