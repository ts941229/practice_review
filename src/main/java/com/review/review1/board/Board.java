package com.review.review1.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "board_seq_generator",
							  sequenceName = "board_seq",
							  initialValue = 1,
							  allocationSize = 1)
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "board_seq_generator")
	private Long id;
	
	private String title;
	
	private String author;
	
	private String content;
	
	
	
}
