package com.asiabill.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asiabill.dto.HelloDto;
import com.asiabill.dto.ParamDto;

@FeignClient("service-server")
public interface HelloFeignInterface {
	
	@RequestMapping(value="/hello",method = RequestMethod.POST)
	HelloDto helloFromClient(@RequestBody ParamDto param);
	
	@RequestMapping(value="/string",method = RequestMethod.POST)
	HelloDto stringTest(@RequestParam("name") String name, @RequestParam("age") String age);
}
