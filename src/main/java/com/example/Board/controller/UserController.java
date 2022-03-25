package com.example.Board.controller;

import com.example.Board.domain.Users;
import com.example.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signUpNewUser(){
        return "users/signup";
    }

    @PostMapping("/signup")
    public String signUpConfirm(Users users){
        userService.joinNewNomalUser(users);
        return "redirect:/";
    }
}