package com.example.clone.service;



import com.example.clone.dto.SignupRequestDto;

import java.util.regex.Pattern;

public class ValidateChecker {
//    public static User userDetailsIsNull(UserDetailsImpl userDetails){
//        if(userDetails != null){
//            return userDetails.getUser();
//        }else{
//            throw new NullPointerException("유효하지 않은 사용자입니다.");
//        }
//    }

    public static void registerValidCheck(SignupRequestDto signupRequestDto) {
        // 회원 ID 중복 확인
        // 1. 닉네임이 3~20자의 대소문자/숫자 조합인지
        // 2. 비밀번호와 비밀번호 확인이 일치하는지
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
//        String passwordcheck = signupRequestDto.getPasswordcheck();

        if (!Pattern.compile(username).matcher(username).find()) {
            throw new IllegalArgumentException("아이디는 숫자와 영문자 조합으로 6~20자리를 사용해야합니다.");
        }
//        else if (!password.equals(passwordcheck)) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
    }

    public static String cleanXSS(String value) {
        // XSS 방어 구문
        //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "＜").replaceAll(">", "＞");
        value = value.replaceAll("\\(", "（").replaceAll("\\)", "）");
        value = value.replaceAll("'", "＇");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "");
        value = value.replaceAll("script", "");
        return value;
    }
}
