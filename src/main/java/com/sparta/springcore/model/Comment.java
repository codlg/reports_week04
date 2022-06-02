package com.sparta.springcore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.springcore.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String comment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "BOARD")
    private Board board;

    public Comment(CommentRequestDto commentRequestDto, Board board){
        this.comment = commentRequestDto.getComment();
        this.nickname = commentRequestDto.getNickname();
        this.board = board;
    }
}
