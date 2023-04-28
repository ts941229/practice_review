package com.review.review1.board.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	public String boardListForm(Model model
										, @PageableDefault(page = 0, size = 5, sort = "id" , direction = Direction.DESC) Pageable pageable) {
		
		Page<Board> boardList = boardService.findAll(pageable);
		
		// resources for pagination
		int pageSize = 5;
		int nowPage = boardList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(((nowPage - 1) / pageSize) * pageSize + 1, 0);
		int endPage = Math.min(startPage + pageSize - 1, boardList.getTotalPages());
		int prev = startPage - pageSize;
		int next = startPage + pageSize;
		
		// 데이터 없는경우
		if(endPage == 0) {endPage = 1;}
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		return "/board/board_list";
	}
	
	@GetMapping("/board-search")
	public String boardSearchForm(Model model
											, @RequestParam("keyword") String keyword
											, @RequestParam("search_category") String search_category
											, @PageableDefault(page = 0, size = 5, sort = "id" , direction = Direction.DESC) Pageable pageable) {
		
		Page<Board> boardList = boardService.findAll(pageable);
		
		switch (search_category) {
			case "title_or_content" : boardList = boardService.findAllByTitleContainingOrContentContaining(keyword, pageable); break;
			case "title" : boardList = boardService.findAllByTitleContaining(keyword, pageable); break;
			case "content" : boardList = boardService.findAllByContentContaining(keyword, pageable); break;
			case "author" : boardList = boardService.findAllByAuthorContaining(keyword, pageable); break;
		}
		
		// resources for pagination
		int pageSize = 5;
		int nowPage = boardList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(((nowPage - 1) / pageSize) * pageSize + 1, 0);
		int endPage = Math.min(startPage + pageSize - 1, boardList.getTotalPages());
		int prev = startPage - pageSize;
		int next = startPage + pageSize;
		
		// 데이터 없는경우
		if(endPage == 0) {endPage = 1;}
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("keyword", keyword);
		model.addAttribute("search_category", search_category);
		
		return "/board/board_search";
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
		
		boardService.deleteById(id);
		
		return "redirect:/board/board-list";
	}
	
}
