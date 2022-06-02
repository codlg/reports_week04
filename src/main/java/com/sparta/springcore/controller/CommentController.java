package com.sparta.springcore.controller;

import com.sparta.springcore.dto.CommentRequestDto;
import com.sparta.springcore.dto.CommentResponseDto;
import com.sparta.springcore.model.Comment;
import com.sparta.springcore.repository.CommentRepository;
import com.sparta.springcore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    public CommentService commentService;
    public CommentRepository commentRepository;

    @PostMapping("/api/comments/{id}")
    public Comment writeComment(@PathVariable Long id , @RequestBody CommentRequestDto commentRequestDto){
        return commentService.writeComment(commentRequestDto, id);
    }

    @GetMapping("/api/comments/{id}")
    public List<CommentResponseDto> readComment(@PathVariable Long id){
        return commentService.readComment(id);
    }

    @DeleteMapping("/api/comments/{coomentid}")
    public void deleteComment(@PathVariable Long commentid){
        commentService.deleteComment(commentid);
    }

    @PutMapping("/api/comments/{commentid}")
    public Comment editComment(@PathVariable Long commentid, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.editComment(commentRequestDto, commentid);
    }
}
