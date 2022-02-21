package com.example.clone.repository;


import com.example.clone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findAllByOrderByCreatedAtDesc();
}
