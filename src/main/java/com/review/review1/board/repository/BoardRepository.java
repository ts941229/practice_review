package com.review.review1.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.review.review1.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	@Query("select b from Board b where b.title like %:keyword% or b.content like %:keyword%")
	Page<Board> findAllByTitleContainingOrContentContaining(@Param("keyword") String keyword, Pageable pageable);
	
	Page<Board> findAllByTitleContaining(String keyword, Pageable pageable);

	Page<Board> findAllByContentContaining(String keyword, Pageable pageable);

	Page<Board> findAllByAuthorContaining(String keyword, Pageable pageable);

	
}
