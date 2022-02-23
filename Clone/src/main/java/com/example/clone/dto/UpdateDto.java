package com.example.clone.dto;


import com.example.clone.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDto {

    private String nickname;
    private String address;
    private String profileImage;


    public void update(UpdateDto updateDto, User user) {
        this.address = getAddress();
        this.nickname = getNickname();
        this.profileImage = getProfileImage();
    }
}
