package egovframework.example.member.dao.impl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.example.member.dao.MemberDao;
import egovframework.example.member.service.MemberMapper;
import egovframework.example.member.service.impl.FindPasswordService;
import egovframework.example.member.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao {

	private static final String namespace = null;
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
		//void
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
	    mapper.savePw(vo);
//	    FindPasswordService passwordService = new FindPasswordService();
//	    String tempPw = passwordService.makeRandomNumber();
//	    vo.setMemPw(tempPw);
//	    System.out.println("//////tempPw :" + tempPw);
//	    mapper.savePw(vo);

//	    return sqlSession.update(namespace+".savePw", vo);
	   }

	
	
}






