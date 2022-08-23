package com.jafa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMaker {
	Criteria criteria;
	int totalCount;
	int startPage;
	int endPage;
	int displayPageNum =10;
	boolean prev;
	boolean next;
	
	public PageMaker(Criteria criteria, int totalCount) {
		
		this.criteria = criteria;
		
		//마지막 페이지
		endPage = (int) Math.ceil(criteria.getPage()/(double)displayPageNum)*displayPageNum;
		
		//시작 페이지
		startPage = endPage - displayPageNum+1;
		
		//실제 존재하는 마지막 페이지
		int tempEndPage = (int) Math.ceil(totalCount/(double)criteria.getPerPageNum());
		
		//마지막 페이지가 실제 존재하는 마지막 페이지보다 클경우
		if (endPage>tempEndPage) {endPage = tempEndPage;}
		
		prev = startPage != 1;
		next = endPage < tempEndPage;
	}
}
