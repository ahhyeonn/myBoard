package egovframework.example.member.service.impl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.member.dao.MemberDao;
import egovframework.example.member.service.MemberService;
import egovframework.example.member.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDao memberDao;
	
	//로그인 체크
	@Override
	public boolean loginCheck(MemberVo vo, HttpSession session) {
		boolean result = memberDao.loginCheck(vo);
		
		if(result) {
			MemberVo vo2 = viewMember(vo);
			
			//세션 변수 등록
			session.setAttribute("memId", vo2.getMemId());
			session.setAttribute("memNm", vo2.getMemNm());
		}
		return result;
		
	}
	
	//로그인 정보
	@Override
	public MemberVo viewMember(MemberVo vo) {
		return memberDao.viewMember(vo);
	}
	
	//로그아웃
	@Override
	public void logout(HttpSession session) {
		session.invalidate(); // 세션 초기화
	 }
	
	//회원가입
	@Override
	public int join(MemberVo vo) {
		return memberDao.join(vo);
	}
	
	//아이디 중복 체크
	@Override
	public int memIdCheck(String memId) {
		return memberDao.memIdCheck(memId);
	}
	
	//이메일 중복 체크
	@Override
	public int memMailCheck(String memMail) {
		return memberDao.memMailCheck(memMail);
	}
	
	
	
	
	
}
