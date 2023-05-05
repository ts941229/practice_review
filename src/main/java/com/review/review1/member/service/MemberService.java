package com.review.review1.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.review.review1.member.Member;
import com.review.review1.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	@Autowired
	private final MemberRepository memberRepository;
	
	public Member findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
	
	public void save(Member member) {
		memberRepository.save(member);
	}
	
	
}
