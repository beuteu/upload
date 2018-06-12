package com.beuteu.domain;

import java.sql.Date;

public class BoardVO {
	
	private int bno;          // 인덱스 
	private String title;     // 타이틀
	private String content;   // 내용
	private String userId;    // 사용자아이디
	private char deleteSe;    // 삭제상태 
	private Date rgsde;       // 등록일자
	private Date updde;       // 수정일자 
	private int viewPoint;    // 히트수
	private int cnt;          // 카운트 서브쿼리 
	private String filepath;  // 첨부파일
	
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getViewPoint() {
		return viewPoint;
	}
	public void setViewPoint(int viewPoint) {
		this.viewPoint = viewPoint;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public char getDeleteSe() {
		return deleteSe;
	}
	public void setDeleteSe(char deleteSe) {
		this.deleteSe = deleteSe;
	}
	public Date getRgsde() {
		return rgsde;
	}
	public void setRgsde(Date rgsde) {
		this.rgsde = rgsde;
	}
	public Date getUpdde() {
		return updde;
	}
	public void setUpdde(Date updde) {
		this.updde = updde;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", deleteSe=" + deleteSe + ", rgsde=" + rgsde + ", updde=" + updde + ", viewPoint=" + viewPoint
				+ ", cnt=" + cnt + ", filepath=" + filepath + "]";
	}
	
}
