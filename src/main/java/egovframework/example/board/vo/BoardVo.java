package egovframework.example.board.vo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardVo {
    
    private int boardNo;
    private String title;
    private String content;
    private String memId;
    private String regDate;
    private MultipartFile uploadFile;
    private String boardDelYn;
    private int viewCount;
    private int cmntCnt;
    
    
    private List<FileVo> fileList;

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getBoardDelYn() {
		return boardDelYn;
	}

	public void setBoardDelYn(String boardDelYn) {
		this.boardDelYn = boardDelYn;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCmntCnt() {
		return cmntCnt;
	}

	public void setCmntCnt(int cmntCnt) {
		this.cmntCnt = cmntCnt;
	}

	public List<FileVo> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVo> fileList) {
		this.fileList = fileList;
	}

	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", memId=" + memId
				+ ", regDate=" + regDate + ", uploadFile=" + uploadFile + ", boardDelYn=" + boardDelYn + ", viewCount="
				+ viewCount + ", cmntCnt=" + cmntCnt + ", fileList=" + fileList + "]";
	}


    
    
    //첨부파일
//  private MultipartFile[] files;
  //파일 여러개
//  private List<FileVo> fileList;
    
    
    
}