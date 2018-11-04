package com.joh.phms.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.phms.commons.ObjectTranslator;
import com.joh.phms.domain.model.CustomerOrderD;
import com.joh.phms.domain.model.JsonResponse;
import com.joh.phms.model.CustomerOrder;
import com.joh.phms.model.DiscountType;
import com.joh.phms.model.Doctor;
import com.joh.phms.model.Product;
import com.joh.phms.service.CustomerOrderService;
import com.joh.phms.service.DiscountTypeService;
import com.joh.phms.service.DoctorService;
import com.joh.phms.service.ProductSevice;
import com.joh.phms.validator.CustomerOrderDValidator;

@Controller
@RequestMapping(path = "/customerOrders")
public class CustomerOrderController {

	private static final Logger logger = Logger.getLogger(CustomerOrderController.class);

	@Autowired
	private ProductSevice productSevice;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private ObjectTranslator objectTranslator;

	@Autowired
	private DiscountTypeService discountTypeService;

	@GetMapping(path = "/add")
	private String getAddingCustomerOrder(Model model) throws JsonProcessingException {

		logger.info("getAddingCustomerOrder->fired");

		Iterable<Doctor> doctors = doctorService.findAll();

		logger.info("doctors=" + doctors);

		Iterable<DiscountType> discountTypes = discountTypeService.findAll();

		logger.info("discountTypes=" + discountTypes);

		ObjectMapper objectMapper = new ObjectMapper();
		model.addAttribute("jsonDoctors", objectMapper.writeValueAsString(doctors));

		model.addAttribute("jsonDiscountTypes", objectMapper.writeValueAsString(discountTypes));

		Iterable<Product> products = productSevice.findAll();
		logger.info("products=" + products);
		model.addAttribute("jsonProducts", objectMapper.writeValueAsString(products));

		return "adminAddCustomerOrder";
	}

	@PostMapping(path = "/add")
	@ResponseBody
	private JsonResponse addCustomerOrder(@RequestBody @Validated() CustomerOrderD customerOrderD) {

		logger.info("getAddingCustomerOrder->fired");

		logger.info("CustomerOrderD=" + customerOrderD);

		CustomerOrder customerOrder = objectTranslator.customerOrderDTocustomerOrder(customerOrderD);

		customerOrder = customerOrderService.save(customerOrder);

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus(200);
		jsonResponse.setMessage("success");
		jsonResponse.setEtc("" + customerOrder.getId());

		return jsonResponse;
	}

	@GetMapping(path = "/edit/{id}")
	private String getUpdatingCustomerOrder(@PathVariable int id, Model model) throws JsonProcessingException {

		logger.info("getUpdatingCustomerOrder->fired");

		ObjectMapper objectMapper = new ObjectMapper();

		Iterable<Doctor> doctors = doctorService.findAll();

		logger.info("doctors=" + doctors);

		model.addAttribute("jsonDoctors", objectMapper.writeValueAsString(doctors));

		CustomerOrder customerOrder = customerOrderService.findOne(id);

		CustomerOrderD customerOrderD = objectTranslator.customerOrderToCustomerOrderD(customerOrder);

		logger.info("customerOrderD=" + customerOrderD);

		model.addAttribute("jsonCustomerOrderD", objectMapper.writeValueAsString(customerOrderD));

		Iterable<DiscountType> discountTypes = discountTypeService.findAll();

		logger.info("discountTypes=" + discountTypes);

		System.out.println(objectMapper.writeValueAsString(discountTypes));

		model.addAttribute("jsonDiscountTypes", objectMapper.writeValueAsString(discountTypes));

		return "adminEditCustomerOrder";
	}

	@PostMapping(path = "/update")
	@ResponseBody
	private JsonResponse udpateCustomerOrder(
			@RequestBody @Validated(CustomerOrderDValidator.ValidationForEdit.class) CustomerOrderD customerOrderD) {

		logger.info("udpateCustomerOrder->fired");

		logger.info("CustomerOrderD=" + customerOrderD);

		CustomerOrder customerOrder = objectTranslator.customerOrderDTocustomerOrder(customerOrderD);

		customerOrder = customerOrderService.update(customerOrder);

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus(200);
		jsonResponse.setMessage("success");
		jsonResponse.setEtc("" + customerOrder.getId());

		return jsonResponse;
	}

	@GetMapping(path = "/delete/{id}")
	private String delete(@PathVariable int id) {

		logger.info("udpateCustomerOrder->fired");

		logger.info("id=" + id);

		customerOrderService.delete(id);

		return "success";
	}

	@GetMapping(path = "/{id}")
	public String getCustomerOrder(@PathVariable int id, Model model) {

		logger.info("getCustomerOrder->fired");

		CustomerOrder customerOrder = customerOrderService.findOne(id);
		logger.info("customerOrder=" + customerOrder);

		model.addAttribute("customerOrder", customerOrder);

		return "customerOrder/getCustomerOrder";
	}

	@GetMapping()
	private String getCustomerOrdersByDate(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getCustomerOrdersByDate->fired");

		List<CustomerOrder> customerOrders = customerOrderService.findAllByOrderTimeBetween(from, to);

		logger.info("customerOrders=" + customerOrders);

		model.addAttribute("customerOrders", customerOrders);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "adminCustomerOrders";
	}

}
