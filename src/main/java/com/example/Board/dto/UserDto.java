package com.example.Board.dto;

import com.example.Board.domain.Permit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String nickname;
    private String password;
    private Permit permit;
}
