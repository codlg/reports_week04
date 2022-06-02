package com.sparta.springcore.service;

import com.sparta.springcore.dto.CommentRequestDto;
import com.sparta.springcore.dto.CommentResponseDto;
import com.sparta.springcore.model.Board;
import com.sparta.springcore.model.Comment;
import com.sparta.springcore.repository.BoardRepository;
import com.sparta.springcore.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {

        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
    }

    //댓글 작성
    public Comment writeComment(CommentRequestDto requestDto, Long Id) {
        Board board = boardRepository.findById(Id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다"));

        Comment comment = new Comment(requestDto, board);
        commentRepository.save(comment);
        return comment;
    }


    // Id에 해당하는 댓글 전체 get
    public List<CommentResponseDto> readComment(Long Id) {
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        Board board= boardRepository.findById(Id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 모임입니다."));

        List<Comment> comments = board.getComments();

        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto =
                    new CommentResponseDto(comment);
            commentResponseDtos.add(commentResponseDto);
        }

//       https://zangzangs.tistory.com/60 : DTO 정렬하기
        return commentResponseDtos.stream().
                sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed()).
                collect(Collectors.toList());
    }


    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("게시물이 존재하지 않습니다")
        );
        commentRepository.delete(comment);
    }

    //수정 하기
    @Transactional
    public  Comment editComment(CommentRequestDto commentRequestDto, long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("코멘트를 찾을 수 없습니다.")
        );
        comment.setComment(commentRequestDto.getComment());
        commentRepository.save(comment);

        return comment;
    }
}

