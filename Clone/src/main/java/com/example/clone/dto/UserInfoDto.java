package com.example.clone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto {

    Long id;
    String username;
    String nickname;
    String profileImage;
    String address;
    int rate;



}
