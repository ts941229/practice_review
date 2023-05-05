package com.review.review1.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.review.review1.member.Member;
import com.review.review1.member.MemberDTO;
import com.review.review1.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	@Autowired
	private final MemberService memberService;
	
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
		System.out.println("password : "+member.getPassword());
		
		
		return "redirect:/";
	}
	
	@GetMapping("/registForm")
	public String registForm() {
		
		return "/member/regist";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute MemberDTO memberDTO) {
		
		
		
		return "redirect:/";
	}
	
	
}
