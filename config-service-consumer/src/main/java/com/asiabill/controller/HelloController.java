package com.asiabill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asiabill.dto.HelloDto;
import com.asiabill.dto.ParamDto;
import com.asiabill.server.impl.FeignTest;

@RestController
public class HelloController {

	@Autowired
	private FeignTest feignTest;
	
	@RequestMapping(value="/sayhello", method = RequestMethod.GET)
	@ResponseBody
	public HelloDto sayHello(String name, String age, String flage) {
		ParamDto param = new ParamDto();
		param.setName(name);
		param.setFlage("1".equals(flage));
		param.setAge(age);
		HelloDto helloDto = feignTest.test(param);
		System.out.println(helloDto);
		return helloDto;
	}
	
	@RequestMapping(value="/string", method = RequestMethod.GET)
	@ResponseBody
	public HelloDto stringTest(String name, String age) {
		HelloDto helloDto = feignTest.stringTest(name, age);
		System.out.println(helloDto);
		return helloDto;
	}
}
