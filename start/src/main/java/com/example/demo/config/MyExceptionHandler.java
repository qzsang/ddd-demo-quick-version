package com.example.demo.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RuntimeException.class)
	public String handleRRException(RuntimeException e){
		return e.getMessage();
	}
}
