package com.bh.springboot.web;

import com.bh.springboot.domain.posts.Posts;
import com.bh.springboot.domain.posts.PostsRepository;
import com.bh.springboot.web.dto.PostsSaveRequestDto;
import com.bh.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown(){
        postsRepository.deleteAll();
    }

    @Test
    public void test_post (){
        // given
        String ingredient = "garlic";
        String recipes = "Aglio e Oilo: YouTuber_Name (https://youtube.com/blah)";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .ingredient(ingredient)
                .recipes(recipes)
                .author("Brian Hwang")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> posts = postsRepository.findAll();
        assertThat(posts.get(0).getRecipes()).isEqualTo(recipes);
        assertThat(posts.get(0).getIngredient()).isEqualTo(ingredient);
        System.out.println(posts.get(0).getId());
    }

    @Test
    public void test_update() {
        //given
        Posts savedPost = postsRepository.save(Posts.builder()
                        .ingredient("Soy sauce")
                        .recipes("Jjim Dak: [Youtuber_name] (https://youtube.com/blah)")
                .build());
        Long updateId = savedPost.getId();
        String expectedIngredient = "Dark soy sauce";
        String expectedRecipes = "Andong jjim Dak: [Youtuber_name2] (https://youtube.com/blah)";

        PostsUpdateRequestDto requestDto =
                PostsUpdateRequestDto.builder()
                        .ingredient(expectedIngredient)
                        .recipes(expectedRecipes)
                        .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                                                                requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> posts = postsRepository.findAll();
        assertThat(posts.get(0).getIngredient()).isEqualTo(expectedIngredient);
        assertThat(posts.get(0).getRecipes()).isEqualTo(expectedRecipes);
    }







} // end of test class
