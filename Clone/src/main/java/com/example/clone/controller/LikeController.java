package com.example.clone.controller;

import com.example.clone.dto.LikeResponseDto;
import com.example.clone.dto.LikeUserDto;
import com.example.clone.model.Likes;
import com.example.clone.security.UserDetailsImpl;
import com.example.clone.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like/{postId}")
    public LikeResponseDto clickLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.clickLike(postId, userDetails);
    }


    @GetMapping("/like/list/{postId}")
    public ResponseEntity<LikeUserDto> getLike(@PathVariable("postId") Long postId){
        return ResponseEntity.ok()
                .body(likeService.getLike(postId));
    }

//    @GetMapping("/like/list/{postId}")
//    public Optional<Likes> getLikes(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return likeService.getLikesList(postId, userDetails);
//
//    }

}
