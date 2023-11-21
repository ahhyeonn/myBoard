package egovframework.example.member.dao;

import javax.servlet.http.HttpSession;

import egovframework.example.member.vo.MemberVo;

public interface MemberDao {
	
	//로그인 체크
	public boolean loginCheck(MemberVo vo);
	
	//로그인 정보
	public MemberVo viewMember(MemberVo vo);
	
	//로그아웃
	public void logout(HttpSession session);
	
	//회원가입 
	public int join(MemberVo vo);
	
	//아이디 중복 체크
	public int memIdCheck(String memId);
	
	//임시 비밀번호로 변경
	public void savePw(MemberVo vo);
	
	//이메일 중복 체크
	public int memMailCheck(String memMail);
	
	
}
