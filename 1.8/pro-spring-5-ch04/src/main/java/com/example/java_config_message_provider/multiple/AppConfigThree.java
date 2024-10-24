package com.example.java_config_message_provider.multiple;

import com.example.java_config_message_provider.model.MessageProvider;
import com.example.java_config_message_provider.model.MessageRenderer;
import com.example.java_config_message_provider.model.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfigFour.class)
public class AppConfigThree {
	final	MessageProvider provider;

	public AppConfigThree(MessageProvider provider) {
		this.provider = provider;
	}


	@Bean(name = "messageRenderer")
	public MessageRenderer messageRenderer() {
		MessageRenderer renderer = new StandardOutMessageRenderer();
		renderer.setMessageProvider(provider);
		return renderer;
	}
}
