package com.example.clone.model;

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
    private float rate = 0;


    @Column
    private String profileImage = "default.img";



    public User(String username, String password, String nickname, String address) {

        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }


    public void update(String nickname, String address,String profileImage) {
        this.nickname = nickname;
        this.address = address;
        this.profileImage=profileImage;
    }

}