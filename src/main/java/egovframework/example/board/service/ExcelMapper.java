package egovframework.example.board.service;

import egovframework.example.board.vo.ExcelVo;

public interface ExcelMapper {
	
	//엑셀 등록
	public int insertExcel(ExcelVo excelVo) throws Exception;
	

}
