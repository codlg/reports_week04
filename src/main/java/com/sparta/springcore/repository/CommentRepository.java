package com.sparta.springcore.repository;

import com.sparta.springcore.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}