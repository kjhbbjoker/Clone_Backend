package com.example.clone.controller;


import com.example.clone.dto.SignupRequestDto;
import com.example.clone.dto.UserInfoDto;
import com.example.clone.security.UserDetailsImpl;
import com.example.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원 로그인 페이지
   /*@GetMapping("/user/loginView")
    public String login() {
        return "login";
    }*/

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }


    //회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
        System.out.println(requestDto);
        userService.registerUser(requestDto);
        System.out.println("회원가입 완료");
        return ResponseEntity.ok()
                .body("회원가입 완료");
    }

    // 로그인 여부 확인
    @GetMapping("/user")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        String nickname = userDetails.getUser().getNickname();
        String profileImage = userDetails.getUser().getProfileImage();
        String address = userDetails.getUser().getAddress();







        ResponseEntity.ok()
                .body("true");
        return new UserInfoDto(username, nickname,profileImage,address);
    }

}