package com.jafa.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int page;
	private int perPageNum;
	private String category; 
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 15;
	}
	
	
	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}


	public int getPageStart() {
		return (this.page-1)*perPageNum;
	}
	
	public void setPage(int page) {
		if (page<=0) {
			this.page=1;
		}else {
			this.page = page;
		}
	}
	
	public String[] getTypeCollection() {
		return type != null ? type.split("") : new String[] {};
	}
}
