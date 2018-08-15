package com.joh.phms.controller;

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

import com.joh.phms.model.ProductCategory;
import com.joh.phms.service.ProductCategorySevice;
import com.joh.phms.validator.ProductCategoryValidator;

@Controller()
@RequestMapping(path = "/productCategories")
public class ProductCategoryController {

	private static final Logger logger = Logger.getLogger(ProductCategoryController.class);

	@Autowired
	private ProductCategorySevice productCategorySevice;

	@GetMapping()
	public String getAllProductCategories(Model model) {
		logger.info("getAllProductCategories->fired");

		Iterable<ProductCategory> productCategories = productCategorySevice.findAll();

		logger.info("productCategories=" + productCategories);

		model.addAttribute("productCategories", productCategories);

		return "adminProductCategories";

	}

	@GetMapping(path = "/add")
	private String getAddingProductCategory(Model model) {
		logger.info("getAddingProductCategory->fired");

		model.addAttribute("productCategory", new ProductCategory());
		return "productCategory/addProductCategory";
	}

	@PostMapping(path = "/add")
	private String addProductCategory(
			@RequestBody @Validated(ProductCategoryValidator.Insert.class) ProductCategory productCategory,
			BindingResult result, Model model) {
		logger.info("addProductCategory->fired");
		
		logger.info("productCategory="+productCategory);

		logger.info("error=" + result.getAllErrors());
		if (result.hasErrors()) {
			model.addAttribute("productCategory", productCategory);
			return "productCategory/addProductCategory";
		} else {
			productCategorySevice.save(productCategory);
			return "success";
		}
	}

	@PostMapping(path = "/delete/{id}")
	private String deleteProdcutCategory(@PathVariable int id) {
		logger.info("deleteProdcutCategory->fired");
		productCategorySevice.delete(id);
		return "success";
	}

	@GetMapping(path = "/edit/{id}")
	private String editingProductCategory(@PathVariable int id, Model model) {
		logger.info("editingProductCategory->fired");
		logger.info("id=" + id);

		ProductCategory productCategory = productCategorySevice.findOne(id);

		logger.info("productCategory=" + productCategory);

		model.addAttribute("productCategory", productCategory);

		return "productCategory/editProductCategory";
	}

	@PostMapping(path = "/update")
	private String updateProductCategory(@RequestBody @Validated(ProductCategoryValidator.Update.class) ProductCategory productCategory, BindingResult result) {
		logger.info("updateProductCategory->fired");

		logger.info("productCategory=" + productCategory);

		logger.info("errors=" + result.getAllErrors());
		if (result.hasErrors()) {
			return "productCategory/editProductCategory";
		} else {
			productCategorySevice.update(productCategory);
			return "success";
		}
	}

}
