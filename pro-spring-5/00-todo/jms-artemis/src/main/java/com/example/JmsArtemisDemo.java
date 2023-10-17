package com.example;

import com.example.config.AppConfig;
import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JmsArtemisDemo {

	public static void main(String... args) throws Exception {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);
		System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
		for (int i = 0; i < 10; ++i) {
			messageSender.sendMessage("Test message: " + i);
		}

		System.in.read();
		ctx.close();
	}
}
