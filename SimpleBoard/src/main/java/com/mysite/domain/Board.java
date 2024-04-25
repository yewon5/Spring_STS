package com.mysite.domain;

//DTO
public class Board {
	private int bNo;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private String bRegdate; //날짜는 문자열로 처리하는 것이 좋다.
	private int bViewcnt;
	
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getbRegdate() {
		return bRegdate;
	}
	public void setbRegdate(String bRegdate) {
		this.bRegdate = bRegdate;
	}
	public int getbViewcnt() {
		return bViewcnt;
	}
	public void setbViewcnt(int bViewcnt) {
		this.bViewcnt = bViewcnt;
	}

	
}
