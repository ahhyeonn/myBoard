package egovframework.example.board.vo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


public class ExcelVo {
	
	private String prodId;
	private String prodName;
	private String prodPrice;
	
	
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}
	
	
	@Override
	public String toString() {
		return "ExcelVo [prodId=" + prodId + ", prodName=" + prodName + ", prodPrice=" + prodPrice + "]";
	}
	
    
}