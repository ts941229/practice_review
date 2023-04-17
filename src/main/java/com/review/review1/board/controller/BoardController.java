package com.review.review1.board.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import oracle.jdbc.proxy.annotation.Methods;

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
		Board board_edited = Board.builder()
												.id(id)
												.title(boardDTO.getTitle())
												.author(board.getAuthor())
												.content(boardDTO.getContent())
												.write_date(board.getWrite_date())
												.edit_date(Util.getInstance().dateFormat(new Date()))
												.build();
		
		boardService.save(board_edited);
		
		return "redirect:/board/board-content/"+board_edited.getId();
	}
	
	@DeleteMapping("/delete/{id}")
	public String boardDelete(@PathVariable("id") Long id) {
		
		System.out.println("boardDelete 탔음 id : "+id);
		
//		boardService.deleteById(id);
		
		return "redirect:/board/board-list";
	}
	
}
