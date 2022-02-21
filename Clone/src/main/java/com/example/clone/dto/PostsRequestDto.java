package com.example.clone.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class PostsRequestDto {

    private String title;

    private String content;

    private float price;

    private String image;

    private String category;


}
