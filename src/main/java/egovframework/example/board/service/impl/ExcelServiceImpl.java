package egovframework.example.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.board.dao.ExcelDao;
import egovframework.example.board.service.ExcelServcie;
import egovframework.example.board.vo.ExcelVo;

@Service
public class ExcelServiceImpl implements ExcelServcie {
	
	@Autowired
	private ExcelDao excelDao;

	//엑셀 등록
	@Override
	public int insertExcel(ExcelVo excelVo) throws Exception {
		return excelDao.insertExcel(excelVo);
	}


}
