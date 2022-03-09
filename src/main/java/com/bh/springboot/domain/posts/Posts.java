package com.bh.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String ingredient;

    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String recipes;

    @Builder
    public Posts(String ingredient, String author, String recipes){
        this.ingredient = ingredient;
        this.author = author;
        this.recipes = recipes;
    }

    public void update(String ingredient, String recipes){
        this.ingredient = ingredient;
        this.recipes = recipes;
    }
}
