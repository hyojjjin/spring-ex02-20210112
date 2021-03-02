  
package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = {200L, 199L, 197L, 196L, 195L};
	//tbl_board에 존재하는 5개 bno들
	
	//dao가 잘 돌아가는 지 테스트
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	//replyMapper가 있는 지 확인ㄴ
	
	@Test
	public void testList() {
		List<ReplyVO> list = mapper.getListWithPaging(null, 200L);
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void testDelete() {
		Long rno = 127L;
		mapper.delete(rno);
	}
	
	@Test
	public void testUpdate() {
		ReplyVO vo = new ReplyVO();
		vo.setRno(4L);
		vo.setReply("수정된 댓글");
		mapper.update(vo);
		
		vo = mapper.read(4L);
		
		assertEquals("수정된 댓글", vo.getReply());

	}
	
	@Test
	public void testRead() {
		Long rno = 6L;
		ReplyVO vo = mapper.read(rno);
		assertEquals("댓글 테스트", vo.getReply());
	}
	
	@Test //책 383쪽
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i  -> {
		//1 ~ 10까지 완성된 스트림이 forEach문의 log.info(i)에 하나씩 들어가며 log가 실행)
			log.info(i + "," + (i % 5));
			
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testCreate2() {
		ReplyVO vo = new ReplyVO();
		//vo.setRno(rno); sequence에서 가져올 거라 필요없음.
		vo.setBno(200L);
		//SELECT * FROM tbl_board ORDER BY bno DESC; 쿼리 실행 후 가장 상단의 값.
		vo.setReply("댓글 테스트");
		vo.setReplyer("user00");
		
		mapper.insert(vo);
		/*
		try {
			vo.setBno(193L); //tbl_board 테이블에 값이 없는 값.
			mapper.insert(vo);
			
			fail();
		} catch (Exception e) {
		}
		*/
	}
	

}