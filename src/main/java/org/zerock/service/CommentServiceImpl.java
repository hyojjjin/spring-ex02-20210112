package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.CommentVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.CommentMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	
	private CommentMapper mapper;

	@Override
	public void register(CommentVO comment) {
		mapper.insertSelectKey(comment);
		
	}
	
	@Override
	public void insert(CommentVO comment) {
		mapper.insert(comment);
	}
	
	
	@Override
	public CommentVO get(Long cno) {
		return mapper.read(cno);
		//mapper에게 read메시지를 보내는 일을 함.
	}
	
	@Override
	public boolean modify(CommentVO comment) {
		return mapper.update(comment) == 1;
		//업데이트한 줄이 1개라는 뜻. update 메소드가 int를 리턴함. 스프링에선 자동으로 줄의 개수를 리턴. 
	}
	
	
	@Override
	public boolean remove(Long cno) {
		return mapper.delete(cno) == 1;
	}
	
	@Override
	public List<CommentVO> getList() {
		return mapper.getList();
	}

}
