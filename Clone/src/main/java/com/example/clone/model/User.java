package com.example.clone.model;

import com.example.clone.dto.UpdateDto;
import com.example.clone.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String address;

    @Column
    private int rate = 36;


    @Column
    private String profileImage = "default.img";






    public User(String username, String password, String nickname, String address) {

        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }


    public void update(UpdateDto updateDto) {

        this.profileImage = updateDto.getProfileImage();
        this.nickname = updateDto.getNickname();
        this.address = updateDto.getAddress();
    }


    public void update(String nickname, String address , String profileImage)
    {
        this.nickname= nickname;
        this.address= address;
        this.profileImage= profileImage;

    }


}