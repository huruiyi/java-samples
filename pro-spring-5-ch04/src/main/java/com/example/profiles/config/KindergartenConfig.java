package com.example.profiles.config;

import com.example.profiles.service.FoodProviderService;
import com.example.profiles.service.impl.KinderGartenFoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kindergarten")
public class KindergartenConfig {

	@Bean
	public FoodProviderService foodProviderService(){
		return new KinderGartenFoodProviderServiceImpl();
	}
}
