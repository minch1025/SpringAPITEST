package com.mincho.Restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
		System.out.println("Hello minchichichichci");
	}
	//@Bean //LocaleResolver is international spring servlet
	//      // If I use Bean annotation, when initialized server every memory will start to fix this task.
	//public LocaleResolver localeResolver(){
//		SessionLocaleResolver localeResolver = new SessionLocaleResolver();/
//		localeResolver.setDefaultLocale(Locale.JAPAN);
//		return localeResolver;
//	}



}
