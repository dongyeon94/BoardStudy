package com.example.Board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String nickname;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Permit permit;
}
