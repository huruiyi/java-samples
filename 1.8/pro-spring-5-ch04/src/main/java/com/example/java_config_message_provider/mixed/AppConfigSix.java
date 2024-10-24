package com.example.java_config_message_provider.mixed;

import com.example.java_config_message_provider.annotated.ConfigurableMessageProvider;
import com.example.java_config_message_provider.model.MessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigSix {

	@Bean
	public MessageProvider provider() {
		return new ConfigurableMessageProvider("Love on the weekend");
	}
}
