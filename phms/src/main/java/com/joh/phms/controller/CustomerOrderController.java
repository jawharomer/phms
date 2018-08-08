package com.joh.phms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.joh.phms.domain.model.CustomerOrderD;
import com.joh.phms.domain.model.CustomerOrderDetailD;
import com.joh.phms.domain.model.JsonResponse;
import com.joh.phms.model.CustomerOrder;
import com.joh.phms.model.CustomerOrderDetail;
import com.joh.phms.model.Doctor;
import com.joh.phms.model.Product;
import com.joh.phms.service.CustomerOrderService;
import com.joh.phms.service.DoctorService;
import com.joh.phms.service.ProductSevice;

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

	@GetMapping()
	private String getAddingCustomerOrder(Model model) throws JsonProcessingException {

		logger.info("getAddingCustomerOrder->fired");

		ObjectMapper objectMapper = new ObjectMapper();

		Iterable<Doctor> doctors = doctorService.findAll();

		logger.info("doctors=" + doctors);

		model.addAttribute("jsonDoctors", objectMapper.writeValueAsString(doctors));

		return "adminAddCustomerOrder";
	}

	@PostMapping(path = "/add")
	@ResponseBody
	private JsonResponse addCustomerOrder(@RequestBody @Valid CustomerOrderD customerOrderD) {

		logger.info("getAddingCustomerOrder->fired");

		logger.info("CustomerOrderD=" + customerOrderD);

		CustomerOrder customerOrder = new CustomerOrder();

		if (customerOrderD.getDoctorId() != null) {
			Doctor doctor = new Doctor();
			doctor.setId(customerOrderD.getDoctorId());
			customerOrder.setDoctor(doctor);
		}

		customerOrder.setCustomerName(customerOrderD.getCustomerName());

		List<CustomerOrderDetail> customerOrderDetails = new ArrayList<>();

		for (CustomerOrderDetailD customerOrderDetailD : customerOrderD.getCustomerOrderDetailDs()) {

			CustomerOrderDetail customerOrderDetail = new CustomerOrderDetail();

			Product product = new Product();
			product.setId(customerOrderDetailD.getProductId());

			customerOrderDetail.setProduct(product);
			customerOrderDetail.setProductCode(customerOrderDetailD.getProductCode());
			customerOrderDetail.setProductName(customerOrderDetailD.getProductName());
			customerOrderDetail.setQuantity(customerOrderDetailD.getQuantity());
			customerOrderDetail.setPrice(customerOrderDetailD.getPrice());

			customerOrderDetails.add(customerOrderDetail);

		}

		customerOrder.setCustomerOrderDetails(customerOrderDetails);

		customerOrder = customerOrderService.save(customerOrder);

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus(200);
		jsonResponse.setMessage("success");
		jsonResponse.setEtc("" + customerOrder.getId());

		return jsonResponse;
	}

	@GetMapping(path = "/{id}")
	public String getCustomerOrder(@PathVariable int id, Model model) {
		
		logger.info("getCustomerOrder->fired");

		CustomerOrder customerOrder = customerOrderService.findOne(id);
		logger.info("customerOrder=" + customerOrder);

		model.addAttribute("customerOrder", customerOrder);

		return "customerOrder/getCustomerOrder";
	}

}
