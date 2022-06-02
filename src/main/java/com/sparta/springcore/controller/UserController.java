package com.sparta.springcore.controller;

import com.sparta.springcore.dto.SignupRequestDto;
import com.sparta.springcore.dto.UserRequestDto;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model) {

        if(errors.hasErrors()){
            model.addAttribute("requestDto", requestDto);

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult .get(key));
            }
            return "signup";
        }
        userService.registerUser(requestDto);
        return "redirect:/user/loginView";
    }

}