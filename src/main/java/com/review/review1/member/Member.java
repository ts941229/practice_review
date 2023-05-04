package com.review.review1.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(allocationSize = 1
							, initialValue = 1
							, sequenceName = "member_seq"
							, name = "member_seq_generator")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
	
	@Builder
	protected Member(Long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	@Id
	@GeneratedValue(generator = "member_seq_generator", strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	private String password;
	
}
