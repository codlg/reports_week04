package com.sparta.springcore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardRequestDto {
    private String writer;
    private String password;
    private String passwordCheck;
    private String title;
    private String contents;
}

