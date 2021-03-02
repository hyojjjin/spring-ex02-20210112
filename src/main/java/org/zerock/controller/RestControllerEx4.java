package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/rest4")
@Log4j
public class RestControllerEx4 {

	@RequestMapping(value = "/ex1", produces = MediaType.TEXT_PLAIN_VALUE)
	//어떤 데이터를 생산하는 지
	public String method1() {
		log.info("method1");

		return "hello world";
	}
	
	@RequestMapping(value = "/ex2", produces = MediaType.APPLICATION_JSON_VALUE)
	//어떤 데이터를 생산하는 지
	public String method2() {
		log.info("method2");

		return "{}";
	}
	
	
	@RequestMapping(value = "/ex3",
			produces = {MediaType.APPLICATION_JSON_VALUE,
						MediaType.APPLICATION_ATOM_XML_VALUE}) //둘 중에 먼저 있는 것이 기본 타입으로 응답됨.
	//어떤 데이터를 생산하는 지
	public Rest1  method3() {
		log.info("method3");

		Rest1 r = new Rest1();
		r.setName("donald");
		r.setAge(33);
		r.setVote(true);
		r.setObj(null);
		
		return r;
	}
	
	@RequestMapping(value = "/ex4", produces = MediaType.TEXT_PLAIN_VALUE)
	//데이터를 보낼때 Accept에  text/plain을 설정해주면. 해당 데이터만 받아서 처리하겠다! 하는 것!
	//produces는 Accept해더와 관련이 있음!
	public String method4() {
		log.info("method4");

		return "hello world";
	}
	
	@RequestMapping(value = "/ex5", produces = "text/plain;charset=UTF-8")
	//한글이 깨질땐 한글이 꺠지지않게 해주장 세미콜론으로 연결할 수 있음.
	public String method5() {
		log.info("method5");

		return "김효진";
	}
	
	
}