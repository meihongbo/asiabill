package com.asiabill.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiabill.dto.HelloDto;
import com.asiabill.dto.ParamDto;
import com.asiabill.server.HelloFeignInterface;

@Component
public class FeignTest {
	
	@Autowired
	private HelloFeignInterface feignInterface;
	
	public HelloDto test(ParamDto param) {
		return feignInterface.helloFromClient(param);
	}
	
	public HelloDto stringTest(String name, String age) {
		return feignInterface.stringTest(name, age);
	}

}
