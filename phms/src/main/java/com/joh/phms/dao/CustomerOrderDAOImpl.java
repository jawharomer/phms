package com.joh.phms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.joh.phms.controller.CustomerOrderController;
import com.joh.phms.model.CustomerOrder;
import com.joh.phms.model.CustomerOrderDetail;

public class CustomerOrderDAOImpl implements CustomerOrderDAOExt {

	private static final Logger logger = Logger.getLogger(CustomerOrderController.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public String delete(int id) {

		CustomerOrder customerOrder = em.find(CustomerOrder.class, id);

		if (customerOrder == null)
			throw new EntityNotFoundException("customerOrder not found with id=" + id);

		for (CustomerOrderDetail customerOrderDetail : customerOrder.getCustomerOrderDetails()) {

			logger.info("customerOrderDetail=" + customerOrderDetail);
			List<Integer> productStepUpIds = customerOrderDetail.getProductStepUpIds();
			logger.info("productStepUpIds=" + productStepUpIds);

			for (Integer productStepUpId : productStepUpIds) {
				Query query = em.createNativeQuery(
						"UPDATE  PRODUCT_STEPUPS SET SOLD_QUANTITY=SOLD_QUANTITY-1  WHERE I_PRODUCT_STEPUP=:id");
				query.setParameter("id", productStepUpId);
				query.executeUpdate();
			}

			em.remove(customerOrderDetail);

		}
		em.remove(customerOrder);

		logger.info("customerOrder=" + customerOrder);

		return null;
	}

}
