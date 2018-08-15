package com.joh.phms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.phms.model.Vendor;
import com.joh.phms.service.VendorService;
import com.joh.phms.validator.VendorValidator.ValidationForEdit;

@Controller()
@RequestMapping(path = "/vendors")
public class VendorController {

	private static final Logger logger = Logger.getLogger(StockController.class);

	@Autowired
	private VendorService vendorService;

	@GetMapping()
	public String getAllVendors(Model model) {
		logger.info("getAllVendors->fired");

		Iterable<Vendor> vendors = vendorService.findAll();

		logger.info("vendors=" + vendors);
		model.addAttribute("vendors", vendors);

		return "adminVendors";

	}

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
			return "vendor/addVendor";
		} else {
			vendorService.save(vendor);
			return "success";
		}
	}

	@PostMapping(path = "/delete/{id}")
	private String deleteVendor(@PathVariable int id) {
		logger.info("deleteVendor->fired");
		vendorService.delete(id);
		return "success";
	}

	@GetMapping(path = "/edit/{id}")
	private String editingVendor(@PathVariable int id, Model model) {
		logger.info("editingVendor->fired");
		logger.info("id=" + id);
		Vendor vendor = vendorService.findOne(id);

		logger.info("vendor=" + vendor);

		model.addAttribute("vendor", vendor);

		return "vendor/editVendor";
	}

	@PostMapping(path = "/update")
	private String updateVendor(@RequestBody @Validated(ValidationForEdit.class) Vendor vendor,
			BindingResult result) {
		logger.info("updateVendor->fired");

		logger.info("vendor=" + vendor);

		logger.info("errors=" + result.getAllErrors());
		if (result.hasErrors()) {
			return "vendor/editVendor";
		} else {
			vendorService.update(vendor);
			return "success";
		}
	}

}
