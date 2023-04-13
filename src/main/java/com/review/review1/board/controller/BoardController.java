package com.review.review1.board.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.review.review1.board.Board;
import com.review.review1.board.BoardDTO;
import com.review.review1.board.repository.BoardRepository;
import com.review.review1.board.service.BoardService;
import com.review.review1.global.Util;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	@Autowired
	private final BoardService boardService;
	
	@GetMapping("/board-list")
	public String boardListForm(Model model) {
		
		model.addAttribute("boardList", boardService.findAll());
		
		return "/board/board_list";
	}
	
	@GetMapping("/board-content/{id}")
	public String boardContentForm(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("board", boardService.findById(id).get());
		
		return "/board/board_content";
	}
	
	@GetMapping("/board-write-form")
	public String boardWriteForm() {
		
		return "/board/board_write";
	}
	
	@PostMapping("/write")
	public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
		
		Board board = Board.builder()
									.title(boardDTO.getTitle())
									.author(boardDTO.getAuthor())
									.content(boardDTO.getContent())
									.write_date(Util.getInstance().dateFormat(new Date()))
									.build();
		
		boardService.save(board);
		
		return "redirect:/board/board-list";
	}
	
	@GetMapping("/board-edit/{id}")
	public String boardEditForm(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("board", boardService.findById(id).get());
		
		return "/board/board_edit";
	}
	
	@PutMapping("/edit/{id}")
	public String boardEdit(@PathVariable("id") Long id, @ModelAttribute BoardDTO boardDTO) {
		
		Board board = boardService.findById(id).get();
		
		board.toBuilder().title(boardDTO.getTitle())
							.content(boardDTO.getContent())
							.edit_date(Util.getInstance().dateFormat(new Date()))
							.build();

		System.out.println("id : "+board.getId());
		System.out.println("title : "+board.getTitle());
		System.out.println("content : "+board.getContent());
		System.out.println("write_date : "+board.getWrite_date());
		System.out.println("edit_date : "+board.getEdit_date());
		
//		boardService.save(board);
		
		return "redirect:/board/board-content/"+boardDTO.getId();
	}
	
}
