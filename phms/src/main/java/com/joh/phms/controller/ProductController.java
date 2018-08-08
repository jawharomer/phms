package com.joh.phms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joh.phms.domain.model.ProductD;
import com.joh.phms.model.Product;
import com.joh.phms.service.ProductSevice;

@Controller()
@RequestMapping(path = "/products")
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductSevice productService;

	@GetMapping(path = "/add")
	private String getAddingProduct(Model model) {
		logger.info("getAddingProduct->fired");
		model.addAttribute("product", new Product());
		return "product/addProduct";
	}

	@PostMapping(path = "/add")
	private String addProduct(@RequestBody @Valid Product product, BindingResult result, Model model) {
		logger.info("addProduct->fired");

		logger.info("error=" + result.getAllErrors());
		if (result.hasErrors()) {
			model.addAttribute("product", product);
			return "product/addProduct";
		} else {
			productService.save(product);
			return "success";
		}
	}

	@PostMapping(path = "/delete/{id}")
	@ResponseBody
	public String deleteProduct(@PathVariable int id) {
		logger.info("deleteProduct->fired");
		logger.info("id=" + id);
		productService.delete(id);
		return "success";
	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingProduct(@PathVariable int id, Model model) {
		logger.info("getEditingProduct->fired");
		model.addAttribute("product", productService.findOne(id));
		return "product/editProduct";
	}

	@PostMapping(path = "/update")
	public String updateProduct(@RequestBody @Valid Product product, BindingResult result, Model model) {
		logger.info("updateProduct->fired");
		logger.info("product=" + product);

		logger.info("error=" + result.getAllErrors());
		if (result.hasErrors()) {
			model.addAttribute("product", product);
			return "product/editProduct";
		} else {
			productService.update(product);
			return "success";
		}

	}

	@GetMapping(path = "/find/code/{code}")
	@ResponseBody
	private ProductD getProductByCode(@PathVariable String code, Model model) {
		logger.info("getProductByCode->fired");

		logger.info("code=" + code);

		ProductD productD = productService.findProductByCode(code);

		logger.info("productD=" + productD);

		return productD;
	}

}
