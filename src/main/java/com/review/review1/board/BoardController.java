package com.review.review1.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/board_list")
	public String BoardPage() {
		return "/board/board_list";
	}
	
	@GetMapping("/board_content")
	public String BoardContentPage() {
		return "/board/board_content";
	}
	
	@GetMapping("/board_write")
	public String BoardWritePage() {
		return "/board/board_write";
	}
	
}
