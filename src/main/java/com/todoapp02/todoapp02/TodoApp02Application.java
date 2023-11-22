package com.todoapp02.todoapp02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class TodoApp02Application {

	public static void main(String[] args) {
		SpringApplication.run(TodoApp02Application.class, args);
	}

	@Bean
	Validator validator() {
		return new LocalValidatorFactoryBean();

	}

}
