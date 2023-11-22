package egovframework.example.board.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.example.board.dao.ExcelDao;
import egovframework.example.board.service.ExcelMapper;
import egovframework.example.board.vo.ExcelVo;

@Repository
public class ExcelDaoImpl implements ExcelDao {
	
	@Autowired
    private SqlSession sqlSession;
	

	@Override
	public int insertExcel(ExcelVo excelVo) throws Exception {
		ExcelMapper mapper = sqlSession.getMapper(ExcelMapper.class);
		return mapper.insertExcel(excelVo);
	}

}
