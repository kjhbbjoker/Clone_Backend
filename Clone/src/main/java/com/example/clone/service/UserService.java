package com.example.clone.service;

import com.example.clone.dto.SignupRequestDto;
import com.example.clone.dto.UserRequestDto;
import com.example.clone.model.User;
import com.example.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {

        ValidateChecker.registerValidCheck(requestDto);

        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        String nickname = requestDto.getNickname();
        Optional<User> found1 = userRepository.findByNickname(nickname);
        if(found1.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 닉네임이 존재합니다.");
        }

        String address = requestDto.getAddress(); // 주소 저장


        String password = passwordEncoder.encode(requestDto.getPassword());//비번 인코딩

        User user = new User(username, password, nickname, address);
        userRepository.save(user);
    }


    @Transactional
    public void editUser(UserRequestDto userRequestDto, Long userId, User user) {
//        Posts posts = new Posts(requestDto, user);


        User user1 = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("해당 유저는 없습니다.")
        );
        user1.update(userRequestDto.getNickname(),userRequestDto.getProfileImage(),userRequestDto.getNickname());
    }


   /*@Transactional
    public void editUser(UserInfoDto userInfoDto) {
//        Posts posts = new Posts(requestDto, user);

        User user = userRepository.findByUsername(username);
        user.update(userInfoDto.getNickname(),userInfoDto.getAddress(),userInfoDto.getProfileImage());
    }*/
}

