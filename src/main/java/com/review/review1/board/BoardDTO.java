package com.review.review1.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	
	private Long id;
	
	private String title;
	
	private String author;
	
	private String content;
	
	private String write_date;
	
	private String edit_date;
}
