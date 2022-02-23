package com.example.clone.service;

import com.example.clone.dto.RateDto;
import com.example.clone.dto.RatedDto;
import com.example.clone.dto.SignupRequestDto;
import com.example.clone.dto.UpdateDto;
import com.example.clone.model.Post;
import com.example.clone.model.User;
import com.example.clone.repository.PostRepository;
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

    private final PostRepository postRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
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
    public void edituser(UpdateDto updateDto, User user) {

        user.update(updateDto.getNickname(),updateDto.getAddress(),updateDto.getProfileImage());
        userRepository.save(user);
    }

    public RatedDto addRate(RateDto rateDto){ //유저 평가하기

        RatedDto ratedDto = new RatedDto();
        Post post = postRepository.findById(rateDto.getPostId()).get();
        post.setRated(true);


        int rate1 = rateDto.getRate();
        User user = userRepository.findById(rateDto.getId()).get();  //평가 점수 더하는 로직
        int currentRate = user.getRate();
        user.setRate(currentRate + rate1);
        User user2 = userRepository.save(user);


        rateDto.setRate(user2.getRate());
        ratedDto.setRated(true);
        return ratedDto;
    }















   /*@Transactional
    public void editUser(UserInfoDto userInfoDto) {
//        Posts posts = new Posts(requestDto, user);

        User user = userRepository.findByUsername(username);
        user.update(userInfoDto.getNickname(),userInfoDto.getAddress(),userInfoDto.getProfileImage());
    }*/
}

