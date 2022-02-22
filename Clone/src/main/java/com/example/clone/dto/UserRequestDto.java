package com.example.clone.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {

    private String nickname;
    private String address;
    private String profileImage;
}
