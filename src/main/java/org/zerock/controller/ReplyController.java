package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies")
@Log4j
@AllArgsConstructor //생성자로 만들어주는 어노테이션
public class ReplyController {

	private ReplyService service;
	//자동으로 주입이됨. 뭐가 주입되는거징..,????
	//AllArgsConstructor 어노테이션으로 인해서 자동으로 주입됨.
	//아래에 있는 모든 곳에서 다 사용할 수 있음.
	
	@PostMapping(value = "/new",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
	
		log.info("vo: " + vo);
		
		int insertCount = service.register(vo);
		//service register 건너건너 가보면 int로 리턴됨.
		//어떤 서비스 인지 어떻게 알지??
		//위에 있당!
		
		if(insertCount == 1) {
			return new ResponseEntity<> ("success9999", HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("page") int page,
			@PathVariable("bno") Long bno) {
		
		Criteria cri = new Criteria(page, 10);
		
		List<ReplyVO> list = service.getList(cri, bno);
		
		log.info(list);
		
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		ReplyVO vo = service.get(rno);
		
		log.info(vo);
		
		return new ResponseEntity<ReplyVO>(vo, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
	//댓글 삭제
	public ResponseEntity<String> remove(
			@PathVariable("rno") Long rno) {
		int cnt = service.remove(rno);
		
		log.info(cnt);
		
		if(cnt == 1) {
			return new ResponseEntity<>("success9999", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{rno}",
			//댓글 수정
			method = {RequestMethod.PUT, RequestMethod.PATCH},
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO vo,
			@PathVariable Long rno) {

		vo.setRno(rno);

		int cnt = service.modify(vo);
		
		log.info(cnt);

		if (cnt == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}