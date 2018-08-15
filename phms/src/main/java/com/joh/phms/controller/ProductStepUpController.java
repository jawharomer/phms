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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joh.phms.domain.model.ProductStepUpD;
import com.joh.phms.model.Product;
import com.joh.phms.model.ProductStepUp;
import com.joh.phms.model.Vendor;
import com.joh.phms.service.ProductSevice;
import com.joh.phms.service.ProductStepUpService;
import com.joh.phms.service.VendorService;

@Controller()
@RequestMapping(path = "/productStepUps")
public class ProductStepUpController {

	private static final Logger logger = Logger.getLogger(ProductStepUpController.class);

	@Autowired
	private ProductSevice productService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private ProductStepUpService productStepUpService;

	@GetMapping(path = "/add/product/{id}")
	private String getAddingProductStepUp(@PathVariable int id, Model model) {
		logger.info("getAddingProductStepUp->fired");

		ProductStepUpD productStepUpD = new ProductStepUpD();

		productStepUpD.setProductId(id);

		Iterable<Vendor> vendors = vendorService.findAll();

		model.addAttribute("vendors", vendors);

		model.addAttribute("productStepUpD", productStepUpD);

		return "productStepUp/addToProduct";
	}

	@PostMapping(path = "/add")
	private String addProductStepUp(@RequestBody @Valid ProductStepUpD productStepUpD, BindingResult result,
			Model model) {
		logger.info("addProductStepUp->fired");

		logger.info("productStepUpD=" + productStepUpD);

		logger.info("error=" + result.getAllErrors());
		if (result.hasErrors()) {

			Iterable<Vendor> vendors = vendorService.findAll();

			model.addAttribute("vendors", vendors);

			model.addAttribute("productStepUpD", productStepUpD);
			return "productStepUp/addToProduct";
		} else {
			ProductStepUp productStepUp = new ProductStepUp();

			Product product = new Product();
			product.setId(productStepUpD.getProductId());

			Vendor vendor = new Vendor();
			vendor.setId(productStepUpD.getVendorId());

			productStepUp.setProduct(product);
			productStepUp.setVendor(vendor);
			productStepUp.setExpirationDate(productStepUpD.getExpirationDate());
			productStepUp.setProductionDate(productStepUpD.getProductionDate());
			productStepUp.setQuantity(productStepUpD.getQuantity());
			productStepUp.setBonusQuantity(productStepUpD.getBonusQuantity());
			productStepUp.setPaymentAmount(productStepUpD.getPaymentAmount());
			productStepUp.setNote(productStepUpD.getNote());

			logger.info("productStepUp=" + productStepUp);

			productStepUpService.save(productStepUp);

			return "success";
		}
	}

	@GetMapping(path = "/delete/{id}")
	private String deleteProductStepUp(@PathVariable int id) {
		logger.info("deleteProductStepUp->fired");

		logger.info("id=" + id);
		productStepUpService.delete(id);
		return "success";
	}

	@GetMapping(path = "/search/orderDate")
	private String searchProductStepUpsByDate(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {

		logger.info("searchProductStepUpsByDate->fired");

		List<ProductStepUp> productStepUps = productStepUpService.findAllByTimeBetween(from, to);
		logger.info("productStepUps=" + productStepUps);

		model.addAttribute("productStepUps", productStepUps);
		model.addAttribute("searchBy", "orderDate");
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "adminProductStepUps";
	}

	@GetMapping(path = "/search/expirationDate")
	private String searchProductStepUpsByExpirationDate(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
			Model model) {

		logger.info("getProductStepUpsByExpirationDate->fired");

		List<ProductStepUp> productStepUps = productStepUpService.findAllByExpirationDateLessThanEqual(to);
		logger.info("productStepUps=" + productStepUps);

		model.addAttribute("productStepUps", productStepUps);
		model.addAttribute("searchBy", "expirationDate");
		model.addAttribute("to", to);

		return "adminProductStepUps";
	}

}
