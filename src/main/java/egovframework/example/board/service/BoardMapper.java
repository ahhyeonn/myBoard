package egovframework.example.board.service;
 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import egovframework.example.board.vo.Search;
import egovframework.example.board.vo.BoardVo;
import egovframework.example.board.vo.CmntVo;
//Mapper namespace 와 ID를 연결할 Interface 를 두어서 interface를 호출하는 방법.
//Mybatis 매핑XML에 기재된 SQL을 호출하기 위한 인터페이스이다.
//SQL id는 인터페이스에 정의된 메서드명과 동일하게 작성한다
public interface BoardMapper {
	
	//게시글 목록
	public List<BoardVo> selectTest(Search search) throws Exception;
    
    //게시글 작성
    public void insertTest(BoardVo boardVo) throws Exception;
    
    //게시글 상세보기
    public BoardVo selectDetail(int boardNo) throws Exception;
    
    //게시글 검색
    public int getBoardListCnt(Search search) throws Exception;
    
    //게시글 수정
    public void updateTest(BoardVo boardVo) throws Exception;
    
    //게시글 삭제
    public void deleteTest(int boardNo) throws Exception;
    
    //댓글 목록
    public List<CmntVo> listCmnt(int boardNo) throws Exception;
    
    //댓글 작성
    public int insertCmnt(CmntVo cmntVo) throws Exception;
    
    //댓글 삭제
    public void deleteCmnt(int cmntNo) throws Exception;
    
    //댓글 수정
    public int updateCmnt(CmntVo cmntVo) throws Exception;
    
    //대댓글 작성
    public int insertReply(CmntVo cmntVo) throws Exception;
    
    //대댓글 목록
	public List<CmntVo> listReply(int boardNo);
	
	//대댓글 수정
	public int updateReply(CmntVo cmntVo) throws Exception;
	
	//댓글 갯수
	public int updateReplyCount(int boardNo) throws Exception;
	
	//차트
	public List<CmntVo> showChart() throws Exception;
    
}
