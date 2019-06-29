package com.notedown;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Configuration
public class NotedownApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotedownApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}

			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addRedirectViewController("/", "index.html");
				registry.addViewController("/index.html").setViewName("index");
				registry.addViewController("/login.html").setViewName("login");
				registry.addViewController("/login-error.html").setViewName("login-error");
			}
		};
	}

	@Bean
	public Module hibernate4Module() {
		return new Hibernate4Module();
	}
}
