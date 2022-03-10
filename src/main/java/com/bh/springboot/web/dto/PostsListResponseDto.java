package com.bh.springboot.web.dto;


import com.bh.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String ingredient;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.ingredient = entity.getIngredient();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
