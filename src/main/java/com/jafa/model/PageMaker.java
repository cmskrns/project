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
		
		endPage = (int) Math.ceil(criteria.getPage()/(double)displayPageNum)*displayPageNum;
		
		startPage = endPage - displayPageNum+1;
		
		int tempEndPage = (int) Math.ceil(totalCount/(double)criteria.getPerPageNum());
		
		if (endPage>tempEndPage) {endPage = tempEndPage;}
		
		prev = startPage != 1;
		next = endPage < tempEndPage;
	}
}
