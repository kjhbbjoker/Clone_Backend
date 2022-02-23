package com.example.clone.dto;

import com.example.clone.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Setter
public class PostsResponseDto {

    private Long postId;

    private String content;

    private String title;//제목

    private int viewCnt;

    private float price;

    private int likeCnt;

    private LocalDateTime createdAt;

    private  String category;

    private String image;
    User user;


    public PostsResponseDto(Long postId, String content, float price, String category, LocalDateTime createdAt, String title, String image, int likeCnt, int viewCnt, User userinfo) {
        this.postId = postId;
        this.content =content;
        this.price = price;
        this.category = category;
        this.createdAt = createdAt;
        this.title = title;
        this.likeCnt = likeCnt;
        this.viewCnt= viewCnt;
        this.image= image;
        this.user = userinfo;
    }
}
