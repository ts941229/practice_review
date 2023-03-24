package com.review.review1.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/board")
	public String BoardPage() {
		return "/board/board";
	}
	
}
