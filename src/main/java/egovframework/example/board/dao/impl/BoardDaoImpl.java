package egovframework.example.board.dao.impl;
 
import java.util.List;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import egovframework.example.board.dao.BoardDao;
import egovframework.example.board.service.BoardMapper;
import egovframework.example.board.vo.Search;
import egovframework.example.board.vo.BoardVo;
import egovframework.example.board.vo.CmntVo;
 
@Repository
public class BoardDaoImpl implements BoardDao {
 
    @Autowired
    private SqlSession sqlSession;
    
    //게시글 목록
    @Override
    public List<BoardVo> selectTest(Search search) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.selectTest(search);
    }
    
    //게시글 작성
    @Override
    public void insertTest(BoardVo boardVo) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.insertTest(boardVo);
    }
    
    //게시글 검색
    @Override
    public int getBoardListCnt(Search search) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.getBoardListCnt(search);
    }
    
    //게시글 상세보기
    @Override
    public BoardVo selectDetail(int boardNo) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.selectDetail(boardNo);
    }
    
    //게시글 수정
    @Override
    public void updateTest(BoardVo boardVo) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.updateTest(boardVo);
    }
    
    //게시글 삭제
    @Override
    public void deleteTest(int boardNo) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.deleteTest(boardNo);
    }
    
    //댓글 목록
    public List<CmntVo> listCmnt(int boardNo) throws Exception {
    	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
    	return mapper.listCmnt(boardNo);
    }
    
    //댓글 작성
    public int insertCmnt(CmntVo cmntVo) throws Exception {
    	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
    	return mapper.insertCmnt(cmntVo);
    }

    //댓글 삭제
    @Override
    public void deleteCmnt(int cmntNo) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.deleteCmnt(cmntNo);
    }

    //댓글 수정
    @Override
    public int updateCmnt(CmntVo cmntVo) throws Exception {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.updateCmnt(cmntVo);
    }    
    
    
    //대댓글 작성
    public int insertReply(CmntVo cmntVo) throws Exception {
    	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
    	return mapper.insertReply(cmntVo);
    }

    //대댓글 목록
	@Override
	public List<CmntVo> listReply(int boardNo) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
    	return mapper.listReply(boardNo);
	}
	
	//대댓글 수정
	@Override
	public int updateReply(CmntVo cmntVo) throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.updateReply(cmntVo);
	}

	//댓글 갯수
	@Override
	public int updateReplyCount(int boardNo) throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.updateReplyCount(boardNo);
	}
	
	//차트
	@Override
	public List<CmntVo> showChart() throws Exception {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.showChart();
	}
	
}






