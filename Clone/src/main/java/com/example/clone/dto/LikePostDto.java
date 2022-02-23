package com.example.clone.dto;


import com.example.clone.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LikePostDto {

    List<Post> posts;
}
