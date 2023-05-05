package com.review.review1.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.review.review1.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	public Member findByEmail(String email);

	
	
}
