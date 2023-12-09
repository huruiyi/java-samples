package vip.fairy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import vip.fairy.config.AppConfig;

public class ScheduleTaskDemo {

	public static void main(String... args) throws Exception {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		System.in.read();
 		ctx.close();
	}
}
