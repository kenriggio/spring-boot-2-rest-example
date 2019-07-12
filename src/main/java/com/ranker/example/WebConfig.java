package com.ranker.example;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(basePackages = "com.ranker.example.api.controller")
public class WebConfig { 
	private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}