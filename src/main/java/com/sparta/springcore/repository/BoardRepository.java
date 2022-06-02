package com.sparta.springcore.repository;

import com.sparta.springcore.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();  // findAll - 찾아라 By OrderBy - 순서대로 정렬해줘 ModifiedAt - 수정된 날짜 기준으로 Desc - 내림차순
}
