package com.review.review1.board.service;

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
	
	public Page<Board> findAllByTitleContainingOrContentContaining(String keyword, Pageable pageable){
		return boardRepository.findAllByTitleContainingOrContentContaining(keyword, pageable);
		
	}
	
	public Page<Board> findAllByTitleContaining(String keyword, Pageable pageable){
		return boardRepository.findAllByTitleContaining(keyword, pageable);
		
	}
	
	public Page<Board> findAllByContentContaining(String keyword, Pageable pageable){
		return boardRepository.findAllByContentContaining(keyword, pageable);
	}
	
	public Page<Board> findAllByAuthorContaining(String keyword, Pageable pageable){
		return boardRepository.findAllByAuthorContaining(keyword, pageable);
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
