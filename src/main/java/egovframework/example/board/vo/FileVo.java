package egovframework.example.board.vo;

public class FileVo {
	
	private int fileNo; //시퀀스, 게시판 첨부파일의 번호
	private String fileName;  //첨부파일이름
	private String uploadPath;  //첨부파일경로
	private String uuidName;  //첨부파일저장이름
	private int boardNo;      //게시글 번호
	private String fileType;  //첨부파일타입
	
	
	//getter setter
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUuid() {
		return uuidName;
	}
	public void setUuid(String uuidName) {
		this.uuidName = uuidName;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
	@Override
	public String toString() {
		return "FileVo [fileNo=" + fileNo + ", fileName=" + fileName + ", uploadPath=" + uploadPath + ", uuidName=" + uuidName
				+ ", boardNo=" + boardNo + ", fileType=" + fileType + "]";
	}
	
	

}
