package com.joh.phms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joh.phms.domain.model.ProductD;
import com.joh.phms.service.ProductServiceImpl;
import com.joh.phms.service.ProductSevice;

@Controller()
@RequestMapping(path = "/stock")
public class StockController {

	private static final Logger logger = Logger.getLogger(StockController.class);

	@Autowired
	private ProductSevice productSevice;

	@GetMapping()
	private String getStock(Model model) {
		logger.info("getStock->fired");

		List<ProductD> productDs = productSevice.findStock();

		logger.info("productDs=" + productDs);

		model.addAttribute("productDs", productDs);

		return "adminRoot";
	}
}
