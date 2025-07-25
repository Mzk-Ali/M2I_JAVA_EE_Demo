package fr.m2i.demo.front.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value = ResponseStatusException.class)
	public ModelAndView errorHandler(ResponseStatusException exception) {
		ModelAndView mav;
		if(exception.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(404))) {
			 mav = new ModelAndView("404");
		} else {
			System.out.println(exception);
			mav = new ModelAndView("error"); // page d'erreur générique
		}
		return mav;
	}
	
}