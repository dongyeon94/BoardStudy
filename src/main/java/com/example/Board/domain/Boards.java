package com.example.Board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Boards {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Users memberId;

    private LocalDate createDate;

    private String title;

    @Lob
    private String content;

    private int view;
}
