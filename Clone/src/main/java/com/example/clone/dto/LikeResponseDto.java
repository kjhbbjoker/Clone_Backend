package com.example.clone.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponseDto {
    private String nickname;
    private boolean islike;
    private int likeCnt;

}
