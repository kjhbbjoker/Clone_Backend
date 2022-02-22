package com.example.clone.controller;


import com.example.clone.config.S3Uploader;
import com.example.clone.dto.PostsRequestDto;
import com.example.clone.dto.SignupRequestDto;
import com.example.clone.dto.UserInfoDto;
import com.example.clone.dto.UserRequestDto;
import com.example.clone.security.UserDetailsImpl;
import com.example.clone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final S3Uploader s3Uploader;


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
                .body("회원가입 성공했습니다아아앙아아아ㅏ ");
    }

    // 로그인 여부 확인
    @GetMapping("/user")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        String nickname = userDetails.getUser().getNickname();
        String profileImage = userDetails.getUser().getProfileImage();
        String address = userDetails.getUser().getAddress();
        float rate = userDetails.getUser().getRate();


        ResponseEntity.ok()
                .body("로그인 성공했씁니다아아아아아");

        return new UserInfoDto(username, nickname,profileImage,address, rate);


    }

    @PutMapping("user/{userId}")
    public ResponseEntity<String>editUser(@RequestPart("file") MultipartFile multipartFile,@PathVariable Long userId,
                                          @RequestPart("user") UserRequestDto userRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String image = s3Uploader.upload(multipartFile,"userImage");
        userRequestDto.setProfileImage(image);
        userService.editUser(userRequestDto,userId, userDetails.getUser());
        return ResponseEntity.ok()
                .body("수정되었습니다 true");
    }



}