package com.sparta.blog.dto;

import com.sparta.blog.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long id;
    private String username;
    private String contents;


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.username = post.getUsername();
        this.contents = post.getContents();
    }

    public PostResponseDto(Long id, String username, String contents) {
        this.id = id;
        this.username = username;
        this.contents = contents;

    }

}
