package com.example.profiles.config;

import com.example.profiles.service.FoodProviderService;
import com.example.profiles.service.impl.HighSchoolFoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("highschool")
public class HighschoolConfig {

	@Bean
	public FoodProviderService foodProviderService(){
		return new HighSchoolFoodProviderServiceImpl();
	}
}
