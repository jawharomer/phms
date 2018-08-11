package com.joh.phms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/test")
public class Test {

	@GetMapping(path = "/")
	@ResponseBody
	private String test() {

		return "test";
	}

}
