package com.bh.springboot.service.posts;

import com.bh.springboot.domain.posts.Posts;
import com.bh.springboot.domain.posts.PostsRepository;
import com.bh.springboot.web.dto.PostsListResponseDto;
import com.bh.springboot.web.dto.PostsResponseDto;
import com.bh.springboot.web.dto.PostsSaveRequestDto;
import com.bh.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no post for this id. \nid#: " + id));

        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("There is no post for this id. \nid#: " + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
