package com.sparta.springcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]){3,}", message = "아이디는 영문 대소문자 또는 숫자를 포함한 3자리 이상이여야합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 4, message = "비밀번호는 4자리 이상이여야합니다.")
    private String password;

    @NotBlank(message = "비밀번호 중복확인세요. ")
    private String passwordCheck;
}