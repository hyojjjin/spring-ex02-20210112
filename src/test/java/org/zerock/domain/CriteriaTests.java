package org.zerock.domain;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class CriteriaTests {

	@Test
	public void test() {
		String type = "TWC";
		log.warn(Arrays.toString(type.split("")));
		//배열의 내용을 출력하려면 Arrays.toString()을 사용함.
		
	}

}
