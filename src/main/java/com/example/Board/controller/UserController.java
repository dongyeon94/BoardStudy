package com.example.Board.controller;

import com.example.Board.config.jwt.JwtProvider;
import com.example.Board.domain.Users;
import com.example.Board.service.MemberrService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final MemberrService memberrService;
    private final String USER_URL_PREFIX = "users/";
    private final JwtProvider jwtProvider;

    @PostMapping("/login2")
    @ResponseBody
    public String ttest() {
        Users users = memberrService.authenticateByEmailAndPassword("dongyeon1994@naver.com", "1q2w3e4r");
        String token = jwtProvider.generateToken(users.getEmail());
        return  token;
    }

    @PostMapping("/test")
    @ResponseBody
    public String  sesst() {
        return "asdfasdfasdf";
    }

    @GetMapping("/login")
    public String userLogin() {
        return "users/login";
    }

    @GetMapping("/signup")
    public String signUpNewUser() {
        return USER_URL_PREFIX + "signup";
    }

    @PostMapping("/signup")
    public String signUpConfirm(Users users) {
        memberrService.joinNewNomalUser(users);
        return "redirect:/";
    }
}