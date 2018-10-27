package com.joh.phms.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.OrderProductStepUpDAO;
import com.joh.phms.dao.ProductStepUpDAO;
import com.joh.phms.exception.CusDataIntegrityViolationException;
import com.joh.phms.model.OrderProductStepUp;
import com.joh.phms.model.ProductStepUp;

@Service
public class OrderProductStepUpServiceImpl implements OrderProductServiceService {

	private static final Logger logger = Logger.getLogger(OrderProductStepUpServiceImpl.class);

	@Autowired
	private OrderProductStepUpDAO orderProductStepUpDAO;

	@Autowired
	private ProductStepUpDAO productStepUpDAO;

	@Override
	public OrderProductStepUp save(OrderProductStepUp orderProductStepUp) {
		return orderProductStepUpDAO.save(orderProductStepUp);
	}

	@Override
	public List<OrderProductStepUp> findAllByOrderTimeBetween(Date from, Date to) {
		return orderProductStepUpDAO.findAllByOrderTimeBetween(from, to);
	}

	@Override
	public OrderProductStepUp findOne(int id) {
		OrderProductStepUp orderProductStepUp = orderProductStepUpDAO.findOne(id);
		Hibernate.initialize(orderProductStepUp.getProductStepUps());
		orderProductStepUp.getProductStepUps().forEach(e -> {
			Hibernate.initialize(e.getProduct());
		});
		return orderProductStepUp;
	}

	@Override
	@Transactional
	public OrderProductStepUp update(OrderProductStepUp orderProductStepUp) {
		OrderProductStepUp oldOrderProductStepUp = orderProductStepUpDAO.findOne(orderProductStepUp.getId());
/*
		for (int i = 0; i < oldOrderProductStepUp.getProductStepUps().size(); i++) {
			ProductStepUp productStepUp = oldOrderProductStepUp.getProductStepUps().get(i);
			if (!isPresent(orderProductStepUp.getProductStepUps(), productStepUp.getId())) {
				try {
					System.err.println("productStepUp.getId()=" + productStepUp.getId());
					productStepUpDAO.delete(productStepUp.getId());
				} catch (DataIntegrityViolationException e) {
					throw new CusDataIntegrityViolationException("Code=" + productStepUp.getProduct().getCode());
				}
			}
		}
		*/
		return orderProductStepUpDAO.save(orderProductStepUp);
	}

	@Override
	public void delete(int id) {
		orderProductStepUpDAO.delete(id);
	}

	// Helper
	private boolean isPresent(final List<ProductStepUp> list, final int id) {
		return list.stream().filter(o -> o.getId() != null && o.getId() == id).findFirst().isPresent();
	}

}
