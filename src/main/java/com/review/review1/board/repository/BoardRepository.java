package com.review.review1.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.review.review1.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	Page<Board> findAllByTitleContaining(String keyword, Pageable pageable);
	
}
