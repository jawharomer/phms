package com.joh.phms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.phms.model.Vendor;
import com.joh.phms.service.VendorService;

@Controller()
@RequestMapping(path = "/vendors")
public class VendorController {

	private static final Logger logger = Logger.getLogger(StockController.class);

	@Autowired
	private VendorService vendorService;

	@GetMapping(path = "/add")
	private String getAddingVendor(Model model) {
		logger.info("getAddingVendor->fired");

		model.addAttribute("vendor", new Vendor());
		return "vendor/addVendor";
	}

	@PostMapping(path = "/add")
	private String addProduct(@RequestBody @Valid Vendor vendor, BindingResult result, Model model) {

		logger.info("addProduct->fired");

		logger.info("error=" + result.getAllErrors());
		if (result.hasErrors()) {
			model.addAttribute("vendor", vendor);
			return "product/addProduct";
		} else {
			vendorService.save(vendor);
			return "success";
		}
	}

}
