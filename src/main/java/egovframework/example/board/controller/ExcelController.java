package egovframework.example.board.controller;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.board.service.ExcelServcie;
import egovframework.example.board.vo.ExcelVo;


@Controller
public class ExcelController {

	@Autowired
	private ExcelServcie excelService;
	
	//엑셀 등록 페이지로 이동
	@RequestMapping(value = "/excel.do")
	public String excelUpload() {
		return "board/excel";
	}
	
	//엑셀 등록
	@PostMapping(value = "/insertExcel.do", consumes = "multipart/form-data")
	public String readExcel(@RequestParam(value = "file", required=false) MultipartFile file, Model model) throws Exception { 
			
			System.out.println("============여기까지 왔니?============");
		
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		    XSSFSheet worksheet = workbook.getSheetAt(0);
		    
		    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
		    	System.out.println("============엑셀 고고============");
		        
		    	ExcelVo excelVo = new ExcelVo();
		           
		        DataFormatter formatter = new DataFormatter();		        
		        XSSFRow row = worksheet.getRow(i);
		        
		        //컬럼명
		        String prodId = formatter.formatCellValue(row.getCell(0));
		        String prodName = formatter.formatCellValue(row.getCell(1));
		        String prodPrice = formatter.formatCellValue(row.getCell(2));
		        
		        excelVo.setProdId(prodId);
		        excelVo.setProdName(prodName);
		        excelVo.setProdPrice(prodPrice);
     
		        excelService.insertExcel(excelVo);
		    } 
		    
		    return "redirect:/excel.do"; 
	}
	
	
}
