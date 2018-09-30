package com.mhb.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.mhb.springBoot.listener.PropertiesListener;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
        // 第四种方式：注册监听器
        application.addListeners(new PropertiesListener("wisely.properties"));
        application.run(args);
	}
}
