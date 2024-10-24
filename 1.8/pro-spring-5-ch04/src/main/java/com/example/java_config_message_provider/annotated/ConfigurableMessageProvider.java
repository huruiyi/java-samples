package com.example.java_config_message_provider.annotated;


import com.example.java_config_message_provider.model.MessageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {

	private final String message;

	public ConfigurableMessageProvider(	@Value("Love on the weekend")String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
