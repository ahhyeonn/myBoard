package egovframework.example.board.service;
 
import java.util.List;

import egovframework.example.board.vo.Search;
import egovframework.example.board.vo.BoardVo;
import egovframework.example.board.vo.CmntVo;
import egovframework.example.board.vo.FileVo;

public interface FileMapper {
	
	public void insertFile(FileVo vo);
	
	public void deleteFile(String fileNo);
	
	public List<FileVo> findByBoardNo(int boardNo);
	
    
}
