package com.example.clone.dto;


import com.example.clone.model.User;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LikeUserDto {

    List<User> users;
}
