package com.bh.springboot.service.posts;

import com.bh.springboot.domain.posts.Posts;
import com.bh.springboot.domain.posts.PostsRepository;
import com.bh.springboot.web.dto.PostsResponseDto;
import com.bh.springboot.web.dto.PostsSaveRequestDto;
import com.bh.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("There is no post for this id. \nid#: " + id));
        posts.update(requestDto.getIngredient(), requestDto.getRecipes());

        return id;
    }

    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("There is no post for this id. \nid#: " + id));
        return new PostsResponseDto(entity);
    }
}
