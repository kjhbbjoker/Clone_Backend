package com.example.clone.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Response
{
    private LocalDateTime commentDate;
    int status;
}
