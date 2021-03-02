package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {

	//컨트롤러의 영향을 받지않고
	//service의 내용을 변경ㅅㅣ켜주기 위해 interface를 생성함.
	
	
	public void register(BoardVO board);
	
//	public List<BoardVO> getList();
	
	public List<BoardVO> getList(Criteria cri);
	
	public BoardVO get(Long bno);
	
	public boolean remove(Long bno);
	
	public boolean modify(BoardVO board);
	
	public int getTotal(Criteria cri);
}
