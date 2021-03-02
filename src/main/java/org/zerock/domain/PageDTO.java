package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
//DTO => 핸드러와 jsp 사이의 주고받는 객체
	
	private int startPage;
	private int endPage;
	
	private boolean prev;
	private boolean next;
	
	private int total;
	
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int) Math.ceil(cri.getPageNum() / 10.0) * 10;
		this.startPage = endPage - 9;
		
		int realEnd = (int) Math.ceil(total * 1.0 / cri.getAmount());
		
		endPage = Math.min(realEnd, endPage);
		
		this.prev = this.startPage > 1;
		this.next = endPage < realEnd;
	}
}

