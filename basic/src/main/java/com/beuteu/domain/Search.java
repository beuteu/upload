package com.beuteu.domain;

public class Search {
	
	private String searchCondition;  // ����Ʈ �з�
	private String searchText;       // �˻��ؽ�Ʈ
	private Integer bno;             // �ѹ�
	
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	
	@Override
	public String toString() {
		return "Search [searchCondition=" + searchCondition + ", searchText=" + searchText + ", bno=" + bno + "]";
	}

}
