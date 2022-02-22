package com.example.clone.repository;

import com.example.clone.model.Post;
import com.example.clone.model.Likes;
import com.example.clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByPost(Post post);
    Optional<Likes> findByUser(User user);

    Optional<Likes> findByUserAndPost(User user, Post post);
//    <LikeUser> findAllBy(User user);

    //List<Likes> findAllByPostId(Long postId);
}
