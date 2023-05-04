package com.review.review1.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.review.review1.member.Member;
import com.review.review1.member.MemberDTO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/member/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDTO memberDTO) {
		
		Member member = Member.builder()
											.email(memberDTO.getEmail())
											.password(memberDTO.getPassword())
											.build();
		
		System.out.println("email : "+member.getEmail());
		System.out.println("email : "+member.getPassword());
		
		
		return "redirect:/";
	}
	
	
}
