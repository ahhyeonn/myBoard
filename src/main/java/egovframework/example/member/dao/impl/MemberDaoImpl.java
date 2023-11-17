package egovframework.example.member.dao.impl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.example.member.dao.MemberDao;
import egovframework.example.member.service.MemberMapper;
import egovframework.example.member.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SqlSession sqlSession;
	
	//로그인 체크
	@Override
	public boolean loginCheck(MemberVo vo) {
		String name = sqlSession.selectOne("loginCheck", vo);
		return (name == null) ? false : true;
	
	}
	
	//로그인 정보
	@Override
	public MemberVo viewMember(MemberVo vo) {
		return sqlSession.selectOne("viewMember",vo);
	}
	
	//로그아웃
	@Override
    public void logout(HttpSession session) {
		
    }
	
	//회원가입
	@Override
	public int join(MemberVo vo) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.join(vo);
	}
	
	//아이디 중복 체크
	@Override
	public int memIdCheck(String memId) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		return mapper.memIdCheck(memId);
	}
	
	//임시 비밀번호로 변경
	@Override
	public void savePw(MemberVo vo) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
	      mapper.savePw(vo);
	   }

	
	
}
