package com.example.clone.dto;


import com.example.clone.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostGetResponse
{
    private int status;

    private User user;

    private PostGetResponseDto getResponseDto;


}