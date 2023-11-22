package egovframework.example.board.dao;

import egovframework.example.board.vo.ExcelVo;

public interface ExcelDao {
	
	//엑셀 등록
	public int insertExcel(ExcelVo excelVo) throws Exception;

}
