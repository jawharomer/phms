package com.joh.phms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.CustomerOrderDAO;
import com.joh.phms.dao.DoctorDAO;
import com.joh.phms.dao.ProductStepUpDAO;
import com.joh.phms.exception.ItemNotAvaiableException;
import com.joh.phms.model.CustomerOrder;
import com.joh.phms.model.CustomerOrderDetail;
import com.joh.phms.model.Doctor;
import com.joh.phms.model.Product;
import com.joh.phms.model.ProductStepUp;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDAO customerOrderDAO;
	@Autowired
	private ProductStepUpDAO productStepUpDAO;

	@Override
	@Transactional
	public CustomerOrder save(CustomerOrder customerOrder) {
		for (CustomerOrderDetail customerOrderDetail : customerOrder.getCustomerOrderDetails()) {

			Product product = null;
			for (int i = 0; i < customerOrderDetail.getQuantity(); i++) {
				ProductStepUp itemForStockDown = productStepUpDAO
						.findProductStepUpForStockDown(customerOrderDetail.getProductCode());
				if (i == 0) {
					product = itemForStockDown.getProduct();
					customerOrderDetail.setProduct(product);
				}

				if (itemForStockDown == null) {
					String message = String.format("This product (%s) is not avaiable enough in the stock",
							product.getCode());
					throw new ItemNotAvaiableException(message);
				}

				customerOrderDetail.getProductStepUpIds().add(itemForStockDown.getId());
				productStepUpDAO.stockDown(itemForStockDown.getId());
			}

		}
		return customerOrderDAO.save(customerOrder);
	}

	@Override
	public CustomerOrder findOne(int id) {
		return customerOrderDAO.findOne(id);
	}

}
