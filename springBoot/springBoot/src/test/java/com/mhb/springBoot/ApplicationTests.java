package com.mhb.springBoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mhb.springBoot.configuration.WiselyConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("---------------------------");
		System.out.println(WiselyConfiguration.getName() + ":" + WiselyConfiguration.getGender() + ":" + WiselyConfiguration.getAge());
		System.out.println("=========================================");
	}

}
