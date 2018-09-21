package com.joh.phms.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	private static final Logger logger = Logger.getLogger(AppController.class);

	@GetMapping("/login")
	public String login() {
		logger.info("login->fired");

		return "login";
	}
	
	@GetMapping("/")
	public String appRoot() {
		logger.info("appRoot->fired");
		return "redirect:/adminRoot";
	}
}
