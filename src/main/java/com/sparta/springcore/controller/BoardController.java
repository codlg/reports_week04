package com.sparta.springcore.controller;


import com.sparta.springcore.dto.BoardRequestDto;
import com.sparta.springcore.model.Board;
import com.sparta.springcore.repository.BoardRepository;
import com.sparta.springcore.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    // 게시글 전체 조회
    @GetMapping("/api/boards")
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 생성
    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) { //@RequestBody - 요청날아올때 넣기 위한 어노테이션
        Board board = new Board(requestDto);

        return boardRepository.save(board);
    }

    // 게시글 수정
    @PutMapping("/api/boards/{id}/check")
    public String updateBoard (@PathVariable Long id ,@RequestBody BoardRequestDto requestDto){

       if(requestDto.getPassword().equals(requestDto.getPasswordCheck())){
           boardService.update(id,requestDto);
       }else{
           return "비밀번호가 틀렸습니다.";
       }
        return "수정 완료";
    }

    // 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) { //@PathVariable - 경로에 있는 변수를 넣는다 {id}

        if(requestDto.getPassword().equals(requestDto.getPasswordCheck())){
            boardRepository.deleteById(id);
        }
        return id;
    }
}
