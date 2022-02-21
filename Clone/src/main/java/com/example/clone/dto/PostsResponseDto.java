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




}
