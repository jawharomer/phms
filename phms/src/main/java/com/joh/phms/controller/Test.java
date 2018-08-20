package com.joh.phms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joh.phms.service.AppUserDetailService;

@Controller
public class Test {
	
	
	@Autowired
	private AppUserDetailService appUserDetailService;

	@GetMapping(path = "/test")
	@ResponseBody
	public String test() {
		
		appUserDetailService.loadUserByUsername("jawhar");
		
		BCryptPasswordEncoder b=new  BCryptPasswordEncoder();
		System.out.println(b.encode("inet2018s"));

		return "success";

	}

	public static void main(String[] args) {
		System.out.println("workds");
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("inet2018s"));
	}
}
