package com.bh.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String ingredient;
    private String recipes;

    @Builder
    public PostsUpdateRequestDto(String ingredient, String recipes){
        this.ingredient = ingredient;
        this.recipes = recipes;
    }
}
