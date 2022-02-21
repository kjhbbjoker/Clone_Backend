package com.example.clone.dto;


import com.example.clone.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostGetResponseDto
{
    private Long postId;
    private String title;
    private String content;
    private float price;
    private LocalDateTime postDate;
    int status;


    public PostGetResponseDto(Post post)
    {
        this.postId = post.getPostId();
        this.postDate = post.getCreatedAt();
        this.title =post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();

    }
}
