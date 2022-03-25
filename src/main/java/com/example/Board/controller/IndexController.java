package com.example.Board.controller;

import org.springframework.stereotype.Controller;

@Controller
public class IndexController {
    public String IndexPage(){
        return "index";
    }
}
