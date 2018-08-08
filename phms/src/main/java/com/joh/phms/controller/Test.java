package com.joh.phms.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joh.phms.dao.ProductDAO;
import com.joh.phms.model.TestModal;
import com.joh.phms.model.TestModelSecond;

@Controller
@RequestMapping(path = "/test")
public class Test {

	@Autowired
	private ProductDAO productDAO;

	@GetMapping()
	private String test(Model model) {
		System.out.println("Test Get running");

		TestModal testModel = new TestModal();
		testModel.setTestModelSeconds(new ArrayList<>());

		TestModelSecond testModelSecond = new TestModelSecond();
		testModelSecond.setGifts("first");
		testModel.getTestModelSeconds().add(testModelSecond);

		System.out.println(testModel);

		model.addAttribute("testModel", testModel);
		return "test";
	}

	@PostMapping()
	private String test(@ModelAttribute("testModel") @Valid TestModal testModal, BindingResult result,@RequestBody String in) {

		System.out.println("Test Post running");
		System.out.println(testModal);
		
		System.out.println("Request in="+in);

		System.out.println("errors="+result.getAllErrors());
		

	

		return "test";
	}

}
