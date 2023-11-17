package egovframework.example.member.controller;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.member.service.MemberMapper;
import egovframework.example.member.service.MemberService;
import egovframework.example.member.service.impl.FindPasswordService;
import egovframework.example.member.service.impl.MailSendService;
import egovframework.example.member.vo.MemberVo;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	MemberService memberService;
	
	@Autowired
	private MailSendService mailService;
	
	@Autowired
	private FindPasswordService findPassword;

	// 로그인 화면
	@RequestMapping(value = "/login.do")
	public String login() {
		return "member/login"; // login.jsp
	}

	// 로그인
	@RequestMapping(value = "/loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute MemberVo vo, HttpSession session) {
//		System.out.println("로그인!!!!!!!!!!!!!!!!");
		boolean result = memberService.loginCheck(vo, session);
		ModelAndView mav = new ModelAndView();
//        System.out.println("result : " + result);

		if (result == true) { // 로그인 성공
			// main.jsp로 이동
			mav.setViewName("member/main");
			mav.addObject("msg", "success");

		} else { // 로그인 실패
			// login.jsp로 이동
			mav.setViewName("member/login");
			mav.addObject("msg", "failure");
		}
		return mav;

	}

	// 로그아웃
	@RequestMapping(value = "/logout.do")
	public ModelAndView logout(HttpSession session) {
		memberService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/logout");
		mav.addObject("msg", "logout");
		return mav;
	}

	// 회원가입 화면으로 가기
	@RequestMapping(value = "/joinForm.do")
	public String joinForm() {
		
//		System.out.println("============회원가입 페이지 왔당============");
		return "member/joinForm";
	}
	
	
	//회원가입
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(MemberVo memberVo) throws Exception {
//		System.out.println("============회원가입 왔당============");
//		System.out.println(memberVo.toString());
//		System.out.println(memberVo.getMemId());
//		System.out.println(memberVo.getMemNm());
		
		memberService.join(memberVo);
		return "redirect:/member/login.do";
		
	}
	
	//아이디 중복 검사
	@RequestMapping(value="/memIdCheck.do", method=RequestMethod.POST)
	@ResponseBody
	public String memIdCheck(String memId) throws Exception {
//		System.out.println("==========idCheck()==========");
		
		int result = memberService.memIdCheck(memId);
//		System.out.println("=============result: " + result);
		
		if(result != 0) {
			return "fail";		//중복 아이디 o
		} else {
			return "success";	//중복 아이디 x
		}
	}
	
	//인증번호 이메일 전송
	@GetMapping("/mailCheck.do")
	@ResponseBody
	public String mailCheck(String email) {
		
//		System.out.println("=========이메일 인증 요청이 들어옴!");
//		System.out.println("=========이메일 주소 : " + email);
		
		return mailService.joinEmail(email);
	}
	
	//임시 비밀번호 이메일 전송
	@GetMapping("/pwCheck.do")
	@ResponseBody
	public String pwCheck(String email) {
		
//		System.out.println("=========임시 비밀번호 이메일 인증 요청이 들어옴!");
//		System.out.println("=========이메일 주소 : " + email);
		
		return findPassword.findPw(email);
	}	
	
	
	//임시 비밀번호로 변경
	/*
	@RequestMapping(value="/savePw.do", method = RequestMethod.POST)
	public void savePw(@ModelAttribute MemberVo memberVo, HttpServletResponse response) throws Exception {
		//당연함 view에서 savePw에 가는게 없는데 여기에 못 들어옴;;;;
		System.out.println("============임시 비밀번호 변경 요청========");
		memberService.savePw(memberVo);
		
	}
	*/
}













