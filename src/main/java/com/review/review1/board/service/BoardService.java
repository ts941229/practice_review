package com.review.review1.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.review.review1.board.Board;
import com.review.review1.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	@Autowired
	private final BoardRepository boardRepository;
	
	public Page<Board> findAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	public void save(Board board) {
		boardRepository.save(board);
	}

	public Optional<Board> findById(Long id) {
		return boardRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}
	
}
