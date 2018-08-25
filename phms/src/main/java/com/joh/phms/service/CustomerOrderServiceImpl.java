package com.joh.phms.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.CustomerOrderDAO;
import com.joh.phms.dao.ProductDAO;
import com.joh.phms.dao.ProductStepUpDAO;
import com.joh.phms.domain.model.ProductD;
import com.joh.phms.exception.CusDataIntegrityViolationException;
import com.joh.phms.exception.ItemNotAvaiableException;
import com.joh.phms.model.CustomerOrder;
import com.joh.phms.model.CustomerOrderDetail;
import com.joh.phms.model.DiscountType;
import com.joh.phms.model.Product;
import com.joh.phms.model.ProductStepUp;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	private static final Logger logger = Logger.getLogger(CustomerOrderServiceImpl.class);

	@Autowired
	private CustomerOrderDAO customerOrderDAO;

	@Autowired
	private ProductStepUpDAO productStepUpDAO;

	@Autowired
	private ProductDAO productDAO;

	@Override
	@Transactional
	public CustomerOrder save(CustomerOrder customerOrder) {
		double totalPrice = 0;
		for (CustomerOrderDetail customerOrderDetail : customerOrder.getCustomerOrderDetails()) {

			Product product = null;

			ProductD productD = productDAO.findProductByCode(customerOrderDetail.getProductCode());

			totalPrice += customerOrderDetail.getQuantity() * productD.getPrice();
			logger.info("customerOrderDetail.getPrice()=" + customerOrderDetail.getPrice());
			logger.info("productD.getPrice()=" + productD.getPrice());

			if (customerOrder.getDiscountType() == null
					&& !customerOrderDetail.getPrice().equals(productD.getPrice())) {
				throw new DataIntegrityViolationException("You are tring to discount without assign discount type");
			} else if (customerOrder.getDiscountType() != null && customerOrder.getDoctor() == null
					&& customerOrder.getDiscountType().getId().equals(DiscountType.type.ByDoctor.getId())) {

				throw new CusDataIntegrityViolationException(
						"You are tring to discount by doctor but no doctor is selected");

			} else if (customerOrder.getDiscountType() != null && (customerOrder.getDiscountAmount() == null
					|| customerOrder.getDiscountAmount().compareTo(BigDecimal.ZERO) == 0)) {
				throw new CusDataIntegrityViolationException(
						"You are tring to discount but discount amount is not set");
			}

			for (int i = 0; i < customerOrderDetail.getQuantity(); i++) {
				ProductStepUp itemForStockDown = productStepUpDAO
						.findProductStepUpForStockDown(customerOrderDetail.getProductCode());

				if (itemForStockDown == null) {
					String message = String.format("This product (%s) is not avaiable enough in the stock",
							customerOrderDetail.getProductCode());
					throw new ItemNotAvaiableException(message);
				}

				if (i == 0 && itemForStockDown != null) {
					product = itemForStockDown.getProduct();
					customerOrderDetail.setProduct(product);
				}

				ProductStepUp productStepUp = new ProductStepUp();
				productStepUp.setId(itemForStockDown.getId());
				customerOrderDetail.getProductStepUpIds().add(productStepUp);
				productStepUpDAO.stockDown(itemForStockDown.getId());
			}

		}

		customerOrder.setTotalPrice(totalPrice);

		return customerOrderDAO.save(customerOrder);
	}

	@Override
	public CustomerOrder findOne(int id) {
		CustomerOrder customerOrder = customerOrderDAO.findOne(id);
		if (customerOrder == null)
			throw new EntityNotFoundException("" + id);
		return customerOrder;
	}

	@Override
	@Transactional
	public CustomerOrder update(CustomerOrder customerOrder) {
		customerOrderDAO.delete(customerOrder.getId());
		customerOrder.setId(customerOrder.getId());
		return save(customerOrder);
	}

	@Override
	public List<CustomerOrder> findAllByOrderTimeBetween(Date from, Date to) {
		return customerOrderDAO.findAllByOrderTimeBetween(from, to);
	}

	@Override
	@Transactional
	public void delete(int id) {
		customerOrderDAO.delete(id);
	}

}
