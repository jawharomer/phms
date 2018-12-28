package com.joh.phms.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.joh.phms.domain.model.ProductD;
import com.joh.phms.model.Country;
import com.joh.phms.model.Product;
import com.joh.phms.model.ProductCategory;
import com.joh.phms.model.ProductUnitType;
import com.joh.phms.service.ProductCategorySevice;
import com.joh.phms.service.ProductService;
import com.joh.phms.service.ProductUnitTypeService;
import com.joh.phms.service.ReportService;
import com.joh.phms.validator.ProductValidation;

@Controller()
@RequestMapping(path = "/products")
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategorySevice productCategorySevice;

	@Autowired
	private ReportService reportService;

	@Autowired
	private ProductUnitTypeService productUnitTypeService;

	@GetMapping(path = "/add")
	private String getAddingProduct(Model model) {
		logger.info("getAddingProduct->fired");

		Iterable<ProductCategory> productCategories = productCategorySevice.findAll();

		logger.info("productCategories=" + productCategories);

		List<Country> countries = reportService.findAllCountry();

		Iterable<ProductUnitType> productUnitTypes = productUnitTypeService.findAll();
		logger.info("productUnitTypes=" + productUnitTypes);

		model.addAttribute("productCategories", productCategories);
		model.addAttribute("countries", countries);
		model.addAttribute("productUnitTypes", productUnitTypes);

		model.addAttribute("product", new Product());

		return "product/addProduct";
	}

	@PostMapping(path = "/add")
	private String addProduct(@RequestBody @Validated(ProductValidation.Insert.class) Product product,
			BindingResult result, Model model) {
		logger.info("addProduct->fired");

		logger.info("product=" + product);

		logger.info("error=" + result.getAllErrors());
		if (result.hasErrors()) {

			Iterable<ProductCategory> productCategories = productCategorySevice.findAll();

			logger.info("productCategories=" + productCategories);

			List<Country> countries = reportService.findAllCountry();

			Iterable<ProductUnitType> productUnitTypes = productUnitTypeService.findAll();
			logger.info("productUnitTypes=" + productUnitTypes);

			model.addAttribute("productCategories", productCategories);
			model.addAttribute("countries", countries);
			model.addAttribute("productUnitTypes", productUnitTypes);

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
		logger.info("id=" + id);
		Iterable<ProductCategory> productCategories = productCategorySevice.findAll();

		logger.info("productCategories=" + productCategories);

		List<Country> countries = reportService.findAllCountry();

		model.addAttribute("productCategories", productCategories);
		model.addAttribute("countries", countries);

		Iterable<ProductUnitType> productUnitTypes = productUnitTypeService.findAll();
		logger.info("productUnitTypes=" + productUnitTypes);
		model.addAttribute("productUnitTypes", productUnitTypes);

		Product product = productService.findOne(id);
		logger.info("product=" + product);

		model.addAttribute("product", product);

		return "product/editProduct";
	}

	@PostMapping(path = "/update")
	public String updateProduct(@RequestBody @Validated(ProductValidation.Update.class) Product product,
			BindingResult result, Model model) {
		logger.info("updateProduct->fired");
		logger.info("product=" + product);

		logger.info("error=" + result.getAllErrors());
		if (result.hasErrors()) {

			Iterable<ProductCategory> productCategories = productCategorySevice.findAll();

			logger.info("productCategories=" + productCategories);

			model.addAttribute("productCategories", productCategories);
			Iterable<ProductUnitType> productUnitTypes = productUnitTypeService.findAll();
			logger.info("productUnitTypes=" + productUnitTypes);
			model.addAttribute("productUnitTypes", productUnitTypes);

			List<Country> countries = reportService.findAllCountry();
			;
			model.addAttribute("countries", countries);

			model.addAttribute("product", product);
			return "product/editProduct";
		} else {
			productService.update(product);
			return "success";
		}

	}

	@GetMapping(path = "/find/code/{code}")
	@ResponseBody
	private ProductD getProductByCode(@PathVariable String code) {
		logger.info("getProductByCode->fired");

		logger.info("code=" + code);

		ProductD productD = productService.findProductByCode(code);

		logger.info("productD=" + productD);

		return productD;
	}

	@GetMapping(path = "/find/productStepUpId/{productStepUpId}")
	@ResponseBody
	private ProductD getProductByProductStepUpId(@PathVariable int productStepUpId) {
		logger.info("getProductByProductStepUpId->fired");

		logger.info("productStepUpId=" + productStepUpId);

		ProductD productD = productService.findProductByProductStepUpId(productStepUpId);

		logger.info("productD=" + productD);

		return productD;
	}

}
