package com.bh.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void test_save_findAll(){

        //given
        String ingredient = "bell pepper";
        String recipes = "Pizza.\n CrazyYouTuber: https://www.youtube.com/awesomepizza";

        postsRepository.save(Posts.builder()
                .ingredient(ingredient)
                .recipes(recipes)
                .author("Brian")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts firstPost = postsList.get(0);
        assertThat(firstPost.getIngredient()).isEqualTo(ingredient);
        assertThat(firstPost.getRecipes()).isEqualTo(recipes);

    }

    @Test
    public void test_BaseTime(){
        // given
        LocalDateTime now = LocalDateTime.of(2022,3,10,11,23,0);
        postsRepository.save(Posts.builder()
                        .ingredient("ingredient123")
                        .recipes("recipes123")
                        .author("Brian")
                        .build());
        // when
        List<Posts> posts = postsRepository.findAll();

        // then
        Posts post = posts.get(0);

        System.out.printf("@@@@@ Created date is:\n%s.\nModified Date is:\n%s\n"
                , post.getCreatedDate()
                , post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
