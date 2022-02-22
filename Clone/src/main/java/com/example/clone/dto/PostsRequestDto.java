package com.example.clone.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class PostsRequestDto {

    private String title;

    private String content;

    private Float price;

    private String image;

    private String category;


}
