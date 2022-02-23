package com.example.clone.dto;


import com.example.clone.model.Post;
import com.example.clone.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter
public class RateDto {

    private Long PostId;//포스트 아이디
    private Long id; //유저 아이디
    private int rate;

}
