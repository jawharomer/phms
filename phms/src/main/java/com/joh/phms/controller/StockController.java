package com.joh.phms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.joh.phms.domain.model.NotificationD;
import com.joh.phms.domain.model.ProductD;
import com.joh.phms.service.ProductSevice;
import com.joh.phms.service.ReportService;

@Controller()
public class StockController {

	private static final Logger logger = Logger.getLogger(StockController.class);

	@Autowired
	private ProductSevice productSevice;

	@Autowired
	private ReportService reportService;

	@GetMapping(path = "/adminRoot")
	private String adminRoot(Model model) {
		logger.info("adminRoot->fired");

		List<NotificationD> notificationDs = reportService.findAdminNotifications();
		logger.info("notificationDs=" + notificationDs);

		model.addAttribute("notificationDs", notificationDs);

		return "adminRoot";
	}

	@GetMapping(path = "/adminStock")
	private String getAdminStock(Model model) {
		logger.info("getAdminStock->fired");

		List<ProductD> productDs = productSevice.findStock();

		logger.info("productDs=" + productDs);

		model.addAttribute("productDs", productDs);

		return "adminStock";
	}
}
