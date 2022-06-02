package com.sparta.springcore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.springcore.dto.BoardRequestDto;
import com.sparta.springcore.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
@Table(name = "BOARD")
public class Board extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)  //ID가 자동으로 생성 및 증가합니다.
    @Id
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @JsonManagedReference
    @OneToMany(mappedBy = "board", orphanRemoval = true)
    List<Comment> comments;

    // 게시글 생성
    public Board(BoardRequestDto requestDto) {
        this.writer = requestDto.getWriter();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    // 게시글 수정
    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
