package com.example.Board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Replys {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Boards boardId;

    @ManyToOne
    private Users memberId;

    @Lob
    private String msg;

    private LocalDate createDate;

}
