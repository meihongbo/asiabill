package com.asiabill.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asiabill.dto.HelloDto;
import com.asiabill.dto.ParamDto;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public HelloDto helloWorld(@RequestBody ParamDto param) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HelloDto helloDto = new HelloDto();
		if (param.getFlage()) {
			helloDto.setName("Test");
		} else {
			helloDto.setName(param.getName());
		}
		helloDto.setAge(param.getAge());
		helloDto.setTime(new Date());
		return helloDto;
	}

	@RequestMapping("/string")
	public HelloDto stringTest(String name, String age) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HelloDto helloDto = new HelloDto();
		helloDto.setName(name);
		helloDto.setAge(age);
		helloDto.setTime(new Date());
		return helloDto;
	}
}
