package com.joh.phms.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.joh.phms.domain.model.JsonResponse;
import com.joh.phms.exception.CusDataIntegrityViolationException;
import com.joh.phms.model.OrderProductStepUp;
import com.joh.phms.model.Product;
import com.joh.phms.model.Vendor;
import com.joh.phms.service.OrderProductServiceService;
import com.joh.phms.service.ProductSevice;
import com.joh.phms.service.VendorService;
import com.joh.phms.validator.OrderProductStepUpValidator;

@Controller()
@RequestMapping(path = "/orderProductStepUps")
public class OrderProductStepUpController {

	private static final Logger logger = Logger.getLogger(OrderProductStepUpController.class);

	@Autowired
	private VendorService vendorService;

	@Autowired
	private OrderProductServiceService orderProductServiceService;

	@Autowired
	private ProductSevice productSevice;

	@GetMapping()
	public String getAllOrderProductStepUp(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllOrderProductStepUp->fired");
		logger.info("from=" + from);
		logger.info("to=" + to);

		List<OrderProductStepUp> orderProductStepUps = orderProductServiceService.findAllByOrderTimeBetween(from, to);
		logger.info("orderProductStepUps=" + orderProductStepUps);

		model.addAttribute("orderProductStepUps", orderProductStepUps);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "orderProductStepUps";
	}

	@GetMapping(path = "/add")
	private String getAddingOrderProductStepUps(Model model) throws JsonProcessingException {
		logger.info("getAddingOrderProductStepUps->fired");

		ObjectMapper objectMapper = new ObjectMapper();

		Iterable<Vendor> vendors = vendorService.findAll();
		logger.info("vendors=" + vendors);

		Iterable<Product> products = productSevice.findAll();
		logger.info("products=" + products);

		model.addAttribute("jsonVendors", objectMapper.writeValueAsString(vendors));
		model.addAttribute("jsonOrderProductStepUp", objectMapper.writeValueAsString(new OrderProductStepUp()));
		model.addAttribute("jsonProducts", objectMapper.writeValueAsString(products));

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

	@GetMapping(path = "/edit/{id}")
	private String getEdittingOrderProductStepUp(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEdittingOrderProductStepUp->fired");
		logger.info("orderProductId=" + id);

		OrderProductStepUp orderProductStepUp = orderProductServiceService.findOne(id);

		logger.info("orderProductStepUp=" + orderProductStepUp);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		Iterable<Vendor> vendors = vendorService.findAll();

		logger.info("vendors=" + vendors);

		Iterable<Product> products = productSevice.findAll();
		logger.info("products=" + products);

		model.addAttribute("jsonVendors", objectMapper.writeValueAsString(vendors));
		model.addAttribute("jsonOrderProductStepUp", objectMapper.writeValueAsString(orderProductStepUp));
		model.addAttribute("jsonProducts", objectMapper.writeValueAsString(products));

		return "adminEditOrderProductStepUp";
	}

	@PostMapping(path = "/edit")
	private String updateOrderProductStepUp(
			@RequestBody @Validated(OrderProductStepUpValidator.Edit.class) OrderProductStepUp orderProductStepUp,
			BindingResult result, Model model) throws JsonProcessingException {
		logger.info("updateOrderProductStepUp->fired");
		logger.info("orderProductStepUp=" + orderProductStepUp);

		orderProductServiceService.update(orderProductStepUp);

		return "success";
	}

	@PostMapping(path = "/delete/{id}")
	private String delete(@PathVariable int id) {
		logger.info("delete->fired");
		logger.info("orderProductStepUpId=" + id);
		orderProductServiceService.delete(id);
		return "success";
	}

}
