package com.joh.phms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.phms.domain.model.JsonResponse;
import com.joh.phms.exception.CusDataIntegrityViolationException;
import com.joh.phms.model.OrderProductStepUp;
import com.joh.phms.model.Vendor;
import com.joh.phms.service.OrderProductServiceService;
import com.joh.phms.service.VendorService;
import com.joh.phms.validator.OrderProductStepUpValidator;

@Controller
@RequestMapping(path = "/orderProductStepUps")
public class OrderProductStepUpController {

	private static final Logger logger = Logger.getLogger(OrderProductStepUpController.class);

	@Autowired
	private VendorService vendorService;

	@Autowired
	private OrderProductServiceService orderProductServiceService;

	@GetMapping(path = "/add")
	private String getAddingOrderProductStepUps(Model model) throws JsonProcessingException {
		logger.info("getAddingOrderProductStepUps->fired");

		ObjectMapper objectMapper = new ObjectMapper();

		Iterable<Vendor> vendors = vendorService.findAll();

		logger.info("vendors=" + vendors);

		model.addAttribute("jsonVendors", objectMapper.writeValueAsString(vendors));
		model.addAttribute("jsonOrderProductStepUp", objectMapper.writeValueAsString(new OrderProductStepUp()));

		return "adminAddOrderProductStepUp";
	}

	@PostMapping(path = "/add")
	private String addOrderProductStepUp(
			@RequestBody @Validated(OrderProductStepUpValidator.Insert.class) OrderProductStepUp orderProductStepUp,
			BindingResult result, Model model) throws JsonProcessingException {
		logger.info("addOrderProductStepUp->fired");
		logger.info("orderProductStepUp=" + orderProductStepUp);
		logger.info("errors=" + result.getAllErrors());
		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("You are ented bad input please fill the data correctly");
		}
		orderProductServiceService.save(orderProductStepUp);
		return "success";
	}

}
