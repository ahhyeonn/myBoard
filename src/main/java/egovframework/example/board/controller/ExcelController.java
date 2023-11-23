package egovframework.example.board.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import java.util.Iterator;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
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
	
	//엑셀 upload
	@PostMapping(value = "/insertExcel.do", consumes = "multipart/form-data")
	public String readExcel(@RequestParam(value = "file", required=false) MultipartFile file, Model model) throws Exception { 
			
//			System.out.println("============여기까지 왔니?============");
//			System.out.println("============file: " + file);
			
			//workbook 생성(.xlsx파일)
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		    XSSFSheet worksheet = workbook.getSheetAt(0);
		    
//		    System.out.println("============여기까지 왔니?2============");
		    
		    //for문 돌려서 반복(용량이 큰 파일에는 적합하지 않음)
		    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
//		    	System.out.println("============엑셀 고고============");
		        
		    	//ExcelVo 가져오기
		    	ExcelVo excelVo = new ExcelVo();
		        DataFormatter formatter = new DataFormatter();
		        
		        //행 생성
		        XSSFRow row = worksheet.getRow(i);
		        
		        //해당 컬럼에 값 넣기
		        String prodId = formatter.formatCellValue(row.getCell(0));
		        String prodName = formatter.formatCellValue(row.getCell(1));
		        String prodPrice = formatter.formatCellValue(row.getCell(2));
		        
		        excelVo.setProdId(prodId);
		        excelVo.setProdName(prodName);
		        excelVo.setProdPrice(prodPrice);
		        
		        //insert 처리
		        excelService.insertExcel(excelVo);
		    } 
		    
		    return "redirect:/excel.do"; 
	}
	
	
	//엑셀 download
	/*
	@GetMapping(value = "/downloadExcel.do")
	public void downloadExcel(HttpServletResponse response) {
		Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("myProd");
        Row row 	= null;
        Cell cell 	= null;
        int rowNum 	= 0;
		
        //행 생성
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("PRODID");
        
        cell = row.createCell(1);
        cell.setCellValue("PRODNAME");
        
        cell = row.createCell(2);
        cell.setCellValue("PRODPRICE");
        
        //컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");
        
        //Excel file output
        wb.write(response.getOutputStream());
        wb.close();
	}
	*/
}








