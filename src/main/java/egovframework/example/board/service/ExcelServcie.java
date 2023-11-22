package egovframework.example.board.service;

import org.springframework.stereotype.Service;

import egovframework.example.board.vo.ExcelVo;

public interface ExcelServcie {
	
	//엑셀 등록
	public int insertExcel(ExcelVo excelVo) throws Exception;
}
