package com.bh.springboot.web.dto;

import com.bh.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String ingredient;
    private String recipes;
    private String author;

    public PostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.ingredient = posts.getIngredient();
        this.recipes = posts.getRecipes();
        this.author = posts.getAuthor();
    }
}
