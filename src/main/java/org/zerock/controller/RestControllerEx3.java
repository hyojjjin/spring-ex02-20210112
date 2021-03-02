package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@RestController //메소드 몸통이 리턴
@RequestMapping("/rest3")
@Log4j
public class RestControllerEx3 {

	@RequestMapping("/ex1")
	public String method1(String name) {
		log.info("name: " + name);
		return "spring";
	}
	
	@RequestMapping("/ex2/{val}") // path variable
	public String method2(@PathVariable("val") String value) {
		//@PathVariable("val") : 주소에 적은 {val}이 value(변수)값으로 들어감.
		log.info("method2");
		log.info(value);
		return "method2";
	}
	
	@RequestMapping("/ex3/{val}")
	public String method3(@PathVariable String val) {
		log.info("method3");
		
		return val;
	}
	
	@RequestMapping("/ex4/{val}/other/{age}")
	public String method4(@PathVariable String val, @PathVariable int age) { //자동 타입 변환까지 해줌
		return val + age;
	}
	
	@RequestMapping("/ex5")
	public String method5(@RequestBody String b) {
		//@RequestBody 리퀘스트 body가 본문으로 들어감
		log.info(b);
		return "method5";
	}
	
	@RequestMapping("/ex6")
	public String method6(@RequestBody Rest1 body) {
		//제이슨이 객체로 전환
		log.info(body);
		return "method6";
	}
	
	//consumes 의 값은 mime type
	//headers의 content-type의 value가 consumes 의 타입과 같을 때 사용 가능함.
	@RequestMapping(path = "/ex7", consumes = "text/plain")
	public String method7(@RequestBody String body) {
		log.info(body);
		
		return "method7";
	}
	

	@RequestMapping(path = "/ex8", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String method8(@RequestBody String body) {
		log.info(body);
		
		return "method8";
	}
	
	//consums는 request header (Content-Type)과 연관있음
	@RequestMapping(path = "/ex9",
			consumes = {MediaType.APPLICATION_JSON_VALUE,
						MediaType.TEXT_PLAIN_VALUE})
	public String method9(@RequestBody String body) {
		log.info(body);
		
		return "method9";
	}
}