package com.sparta.springcore.service;

import com.sparta.springcore.dto.BoardRequestDto;
import com.sparta.springcore.model.Board;
import com.sparta.springcore.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor  // 생성자 어노테이션
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional  // DB에 반영 되기 위한 어노테이션
    public Long update(Long id, BoardRequestDto requestDto) {  // public 반환타입 메소드이름 (재료) {}

        String password = requestDto.getPassword();
        String checkPassword = requestDto.getPasswordCheck();

        Board board = boardRepository.findById(id).orElseThrow(  // orElseThrow - 예외처리
                () -> new IllegalArgumentException("작성자가 아닙니다.")
        );
        if(!password.equals(checkPassword)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        board.update(requestDto);
        return board.getId();
    }
}

