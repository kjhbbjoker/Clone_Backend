package com.example.clone.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {


    private String title;
    private String content;
    private float price;
    private boolean state;

}
