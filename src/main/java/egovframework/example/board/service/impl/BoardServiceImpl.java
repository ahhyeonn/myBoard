package egovframework.example.board.service.impl;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.board.dao.BoardDao;
import egovframework.example.board.service.BoardService;
import egovframework.example.board.vo.Search;
import egovframework.example.board.vo.BoardVo;
import egovframework.example.board.vo.CmntVo;
 
@Service
public class BoardServiceImpl implements BoardService{
 
    @Autowired
    private BoardDao boardDao;
    
    //게시글 목록
    @Override
    public List<BoardVo> selectTest(Search search) throws Exception {
        return boardDao.selectTest(search);
    }
    
    //게시글 작성
    @Override
    public void insertTest(BoardVo boardVo) throws Exception {
        boardDao.insertTest(boardVo);
    }
    
    //게시글 상세보기
    @Override
    public BoardVo selectDetail(int boardNo) throws Exception {
        return boardDao.selectDetail(boardNo);
    }
    
    //게시글 검색
    @Override
    public int getBoardListCnt(Search search) throws Exception {
        return boardDao.getBoardListCnt(search);
    }
    
    //게시글 수정
    @Override
    public void updateTest(BoardVo boardVo) throws Exception {
        boardDao.updateTest(boardVo);
    }
    
    //게시글 삭제
    @Override
    public void deleteTest(int boardNo) throws Exception {
        boardDao.deleteTest(boardNo);
    }
    
    //댓글 목록
    @Override
    public List<CmntVo> listCmnt(int boardNo) throws Exception{
    	return boardDao.listCmnt(boardNo);
    }
    
    //댓글 작성
    public int insertCmnt(CmntVo cmntVo) throws Exception {
    	return boardDao.insertCmnt(cmntVo);
    }

    //댓글 삭제
    @Override
    public void deleteCmnt(int cmntNo) throws Exception {
        boardDao.deleteCmnt(cmntNo);
    }
    
    //댓글 수정
    @Override
    public int updateCmnt(CmntVo cmntVo) throws Exception {
        return boardDao.updateCmnt(cmntVo);
    }    
 
    //댓글 작성
    public int insertReply(CmntVo cmntVo) throws Exception {
    	return boardDao.insertReply(cmntVo);
    }
    
    //대댓글 목록
	@Override
	public List<CmntVo> listReply(int boardNo) {
		return boardDao.listReply(boardNo);
	}
	
	//대댓글 수정
	@Override
	public int updateReply(CmntVo cmntVo) throws Exception {
		return boardDao.updateReply(cmntVo);
	}

//	@Override
//	public int viewCount(int boardNo) throws Exception {
//		return boardDao.viewCount(boardNo);
//	}    
    
	//댓글 갯수
	@Override
	public int updateReplyCount(int boardNo) throws Exception {
		return boardDao.updateReplyCount(boardNo);
	}
	
	//차트
	@Override
	public List<CmntVo> showChart() throws Exception {
		return boardDao.showChart();
	}
	
	
}



