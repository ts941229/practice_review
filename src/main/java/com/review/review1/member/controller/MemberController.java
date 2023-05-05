package com.review.review1.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String regist(@ModelAttribute MemberDTO memberDTO, Model model) {
		
		// 해당 이메일로 검색된 member가 없다면 가입
		if(memberService.findByEmail(memberDTO.getEmail())==null) {
			
			Member member = Member.builder()
					.email(memberDTO.getEmail())
					.password(memberDTO.getPassword())
					.build();
			
			memberService.save(member);
			
			return "redirect:/";
		}else {
			model.addAttribute("isDeny", "yes");
			return "/member/regist";
		}
		
		
		
	}
	
	
}
