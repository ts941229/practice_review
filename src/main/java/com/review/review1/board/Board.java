package com.review.review1.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "board_seq_generator",
							  sequenceName = "board_seq",
							  initialValue = 1,
							  allocationSize = 1)
public class Board {
	
	@Builder
	protected Board(Long id, String title, String author, String content, String write_date, String edit_date) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.write_date = write_date;
		this.edit_date = edit_date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "board_seq_generator")
	private Long id;
	
	private String title;
	
	private String author;
	
	private String content;
	
	private String write_date;
	
	@Nullable
	private String edit_date;
	
	
}
