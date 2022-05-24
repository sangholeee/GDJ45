package com.goodee.ex11.util;

public class PageUtils {

	/*
		- 전체 11개 레코드를
		- 한 페이지에 3개씩 표시한다면
		- 전체 페이지는 4페이지가 된다.
	*/
	
	private int totalRecord;          // DB에서 구해온다.
	private int recordPerPage = 5;    // 여기서 마음대로 정한다. 
	private int totalPage;            // totalRecord와 recordPerPage로 계산한다.
	
	/*
		- 전체 11개 레코드를
		- 한 페이지에 3개씩 표시한다면
		- 각 페이지에 표시되는 레코드의 번호는 다음과 같다.
		page = 1, beginRecord = 1, endRecord = 3
		page = 2, beginRecord = 4, endRecord = 6
		page = 3, beginRecord = 7, endRecord = 9
		page = 4, beginRecord = 10, endRecord = 11 (endRecord = 12가 아님을 주의)
	*/
	
	private int page;                 // 파라미터로 받아온다.
	private int beginRecord;          // page와 recordPerPage로 계산한다.
	private int endRecord;            // beginRecord와 recordPerPage와 totalPage로 계산한다.
	
	
	
	private int pagePerblock;
	private int beginPage;
	private int endPage;
	
	
	
	// totalRecord : DB에서 가져온다.
	// page : 파라미터로 가져온다.
	public void setPageEntity(int totalRecord, int page) {
		
		// totalRecord, page 필드 값 저장
		this.totalRecord = totalRecord;
		this.page = page;
		
		// totalPage 필드 값 계산
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		
		// beginRecord, endRecord
		beginRecord = (page - 1) * recordPerPage + 1;
		endRecord = beginRecord + recordPerPage - 1;
		if(endRecord > totalRecord) {
			endRecord = totalRecord;
		}
		
		
	}


	// Getter/Setter
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getRecordPerPage() {
		return recordPerPage;
	}
	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getBeginRecord() {
		return beginRecord;
	}
	public void setBeginRecord(int beginRecord) {
		this.beginRecord = beginRecord;
	}
	public int getEndRecord() {
		return endRecord;
	}
	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}
	public int getPagePerblock() {
		return pagePerblock;
	}
	public void setPagePerblock(int pagePerblock) {
		this.pagePerblock = pagePerblock;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
	
	
	
	
	
	
	
	
	
}
