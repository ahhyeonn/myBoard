package egovframework.example.board.vo;

import java.util.Date;

public class CmntVo {
	
	private int cmntNo;         //댓글번호
	private int boardNo;		//게시글번호
	private String cmntContent; //댓글내용
	private Date cmntDate;		//댓글작성일
	private String memId;
	private int upCmntNo;		//상위댓글번호
	private int cmntDepth;		//댓글깊이
	private int cmntGroup;		//상위댓글번호
	private String cmntDelYn;   //삭제여부
	private int reCnt;			//댓글수
    private String reDate1;		//날짜
    
	public int getCmntNo() {
		return cmntNo;
	}
	public void setCmntNo(int cmntNo) {
		this.cmntNo = cmntNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getCmntContent() {
		return cmntContent;
	}
	public void setCmntContent(String cmntContent) {
		this.cmntContent = cmntContent;
	}
	public Date getCmntDate() {
		return cmntDate;
	}
	public void setCmntDate(Date cmntDate) {
		this.cmntDate = cmntDate;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getUpCmntNo() {
		return upCmntNo;
	}
	public void setUpCmntNo(int upCmntNo) {
		this.upCmntNo = upCmntNo;
	}
	public int getCmntDepth() {
		return cmntDepth;
	}
	public void setCmntDepth(int cmntDepth) {
		this.cmntDepth = cmntDepth;
	}
	public int getCmntGroup() {
		return cmntGroup;
	}
	public void setCmntGroup(int cmntGroup) {
		this.cmntGroup = cmntGroup;
	}
	public String getCmntDelYn() {
		return cmntDelYn;
	}
	public void setCmntDelYn(String cmntDelYn) {
		this.cmntDelYn = cmntDelYn;
	}
	public int getReCnt() {
		return reCnt;
	}
	public void setReCnt(int reCnt) {
		this.reCnt = reCnt;
	}
	public String getReDate1() {
		return reDate1;
	}
	public void setReDate1(String reDate1) {
		this.reDate1 = reDate1;
	}
	
	@Override
	public String toString() {
		return "CmntVo [cmntNo=" + cmntNo + ", boardNo=" + boardNo + ", cmntContent=" + cmntContent + ", cmntDate="
				+ cmntDate + ", memId=" + memId + ", upCmntNo=" + upCmntNo + ", cmntDepth=" + cmntDepth + ", cmntGroup="
				+ cmntGroup + ", cmntDelYn=" + cmntDelYn + ", reCnt=" + reCnt + ", reDate1=" + reDate1 + "]";
	}
	
	
	
	
	
}
