package com.joh.phms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joh.phms.dao.ReportDAO;

@Controller
@RequestMapping(path = "/test")
public class Test {

	@Autowired
	private ReportDAO reportDAO;

	@GetMapping(path = "/")
	@ResponseBody
	private String test() {
		System.out.println(reportDAO.findAdminNotifications());
		return "test";
	}

}
