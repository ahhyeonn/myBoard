package egovframework.example.member.service;

import javax.servlet.http.HttpSession;

import egovframework.example.member.vo.MemberVo;

public interface MemberMapper {
	
	String loginCheck(MemberVo memVo);

	MemberVo viewMember(MemberVo memVo);
	
	void logout(HttpSession session);
	
	int register(MemberVo vo);
	
	int idCheck(String memId);	
	
	//회원가입
	int join(MemberVo vo);
	
	//아이디 중복 검사
	public int memIdCheck(String memId);
	
	//임시 비밀번호로 변경
	public void savePw(MemberVo vo);
	
	//이메일 중복 검사
	public int memMailCheck(String memMail);

}
