package com.joh.phms.commons;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.joh.phms.controller.CustomerOrderController;
import com.joh.phms.domain.model.CustomerOrderD;
import com.joh.phms.domain.model.CustomerOrderDetailD;
import com.joh.phms.model.CustomerOrder;
import com.joh.phms.model.CustomerOrderDetail;
import com.joh.phms.model.DiscountType;
import com.joh.phms.model.Doctor;
import com.joh.phms.model.Product;

@Component
public class ObjectTranslator {

	private static final Logger logger = Logger.getLogger(CustomerOrderController.class);

	public CustomerOrderD customerOrderToCustomerOrderD(CustomerOrder customerOrder) {

		CustomerOrderD customerOrderD = new CustomerOrderD();

		customerOrderD.setCustomerOrderId(customerOrder.getId());
		customerOrderD.setCustomerName(customerOrder.getCustomerName());
		customerOrderD.setCustomerOrderId(customerOrder.getId());

		if (customerOrder.getDiscountType().getId() != null)
			customerOrderD.setDiscountId(customerOrder.getDiscountType().getId());

		if (customerOrder.getDoctor() != null)
			customerOrderD.setDoctorId(customerOrder.getDoctor().getId());

		if (customerOrder.getDiscountType() != null)
			customerOrderD.setDiscountId(customerOrder.getDiscountType().getId());

		for (CustomerOrderDetail customerOrderDetail : customerOrder.getCustomerOrderDetails()) {
			CustomerOrderDetailD customerOrderDetailD = new CustomerOrderDetailD();

			logger.info("customerOrderDetailD=" + customerOrderDetailD);

			customerOrderDetailD.setProductId(customerOrderDetail.getProduct().getId());
			customerOrderDetailD.setCustomerOrderId(customerOrderDetail.getId());
			customerOrderDetailD.setProductCode(customerOrderDetail.getProductCode());
			customerOrderDetailD.setProductName(customerOrderDetail.getProductName());
			customerOrderDetailD.setQuantity(customerOrderDetail.getQuantity());
			customerOrderDetailD.setPrice(customerOrderDetail.getPrice());

			customerOrderD.getCustomerOrderDetailDs().add(customerOrderDetailD);
		}

		return customerOrderD;
	}

	public CustomerOrder customerOrderDTocustomerOrder(CustomerOrderD customerOrderD) {

		CustomerOrder customerOrder = new CustomerOrder();

		System.out.println(customerOrderD);

		if (customerOrderD.getCustomerOrderId() != null)
			customerOrder.setId(customerOrderD.getCustomerOrderId());

		if (customerOrderD.getDoctorId() != null) {
			Doctor doctor = new Doctor();
			doctor.setId(customerOrderD.getDoctorId());
			customerOrder.setDoctor(doctor);
		}

		if (customerOrderD.getDiscountId() != null) {
			DiscountType discountType = new DiscountType();
			discountType.setId(customerOrderD.getDiscountId());
			customerOrder.setDiscountType(discountType);
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

		return customerOrder;
	}
}
