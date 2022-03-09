package com.bh.springboot.web.dto;

import com.bh.springboot.domain.posts.Posts;
import javafx.geometry.Pos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String ingredient;
    private String recipes;
    private String author;

    @Builder
    public PostsSaveRequestDto(String ingredient, String recipes, String author){
        this.ingredient = ingredient;
        this.recipes = recipes;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .ingredient(ingredient)
                .recipes(recipes)
                .author(author)
                .build();
    }
}
