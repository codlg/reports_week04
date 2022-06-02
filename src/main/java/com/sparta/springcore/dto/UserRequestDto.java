package com.sparta.springcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserRequestDto {
        private String username;
        private String password;

        public UserRequestDto(String username) {
             this.username = username;
        }
}
