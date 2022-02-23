package com.example.clone.dto;


import com.example.clone.model.Likes;
import com.example.clone.model.Post;
import com.example.clone.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class LikeRequestDto {
    public Likes toEntity(Post post, User user) {
        return Likes.builder()
                .user(user)
                .post(post)
                .build();
    }
}
