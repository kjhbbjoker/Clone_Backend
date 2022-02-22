package com.example.clone.dto;


import com.example.clone.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDto {

    private String nickname;
    private String address;
    private String profileImage;


}
