package com.review.review1.member.controller;


import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
	public String regist(@ModelAttribute @Validated MemberDTO memberDTO, BindingResult bindingResult, Model model) {
		
		if(memberService.findByEmail(memberDTO.getEmail())!=null) {
			FieldError fieldError = new FieldError("memberDTO", "email", "중복된 이메일 입니다.");
			bindingResult.addError(fieldError);
		}
		
		if(bindingResult.hasErrors()) {
			
			// 회원가입 실패시 작성한 dto폼 유지
			model.addAttribute("memberDTO", memberDTO);
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				// 유효성 에러 발생시 키값 : valid_필드명 , 밸류 : 해당 에러 메시지로 어트리뷰트 보냄 
				// ex ) valid_password , 비밀번호를 입력하세요.
				model.addAttribute("valid_"+error.getField(), error.getDefaultMessage());
			}
			
			return "/member/regist";
			
		}else {
			Member member = Member.builder()
					.email(memberDTO.getEmail())
					.password(memberDTO.getPassword())
					.build();
			
			memberService.save(member);
			return "redirect:/";
		}
		
	}
	
	
}
