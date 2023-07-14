package com.sparta.blog.service;

import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.entity.Post;
import com.sparta.blog.repository.PostRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {

        this.postRepository = postRepository;

    }
    public PostResponseDto createPost(PostRequestDto requestDto) {

        Post post = new Post(requestDto);

        Post savePost = postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        return postResponseDto;
    }

    public List<PostResponseDto> getPost() {

        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {

        Post post = findPost(id);

        post.update(requestDto);

        return id;
    }

    @Transactional
    public Optional<Post> readPost(Long id, PostRequestDto requestDto) {

        Post post = findPost(id);

        post.read(requestDto);

        return postRepository.findById(id);
    }

    public Long deletePost(Long id) {

        Post post = findPost(id);

        postRepository.delete(post);

        return id;
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }
}
