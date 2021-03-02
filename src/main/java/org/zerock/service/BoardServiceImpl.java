package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;

//@Component
//bean으로 객체로 변환. 웹 요청과 응답을 처리함.

@Service
//bean객체로 변환, 내부 자바 로직을 처리함.
//@Service 내에 @Component가 포함되어 있음
//@component, @Service 구분해주는 이유 : 어떻게 쓰이는 지 명시적으로 보기 위함.
@AllArgsConstructor
//생성자를 자동으로 생성해주는 어노테이션.
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;
	//단일 생성자므로 생성자는 묵시적으로 주입되어서
	//아래 내용은 생략 가능.
	
	
//	//@Autowired 이 버전에선 어노테이션을 생략해도됨.
//	public BoardServiceImpl(BoardMapper mapper) {
//		//생성자를 만드는 방법을 사용.
//		this.mapper = mapper;
//	}
	
	//mapper.read(33);
	
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
	};
	
//	@Override
//	public List<BoardVO> getList() {
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}
	
	@Override
	public boolean remove(Long bno) {
		return mapper.delete(bno) == 1;
	}
	
	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board) == 1;
	}
	
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
}
