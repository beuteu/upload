package com.beuteu.domain;

public class PageMaker {

	private int page;
	private int totalCount;
	private boolean prev, next;
	private int start, end;

	public PageMaker(int page, int totalCount) {
		this.page = page;
		this.totalCount = totalCount;

		double tempEnd = Math.ceil(page / 10.0) * 10;
														
		this.start = (int) tempEnd - 9;

		double realEnd = Math.ceil(totalCount / 10.0);
														
		if (realEnd > tempEnd) {
			this.next = true;
			this.end = (int) tempEnd;
		} else {
			this.end = (int) realEnd;
		}
		if (start != 1) {
			this.prev = true;
		}

	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public static void main(String[] args) {
		PageMaker p = new PageMaker(131, 141);

	}
}
