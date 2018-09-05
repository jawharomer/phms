package com.joh.phms.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.OrderProductStepUpDAO;
import com.joh.phms.model.OrderProductStepUp;

@Service
public class OrderProductStepUpServiceImpl implements OrderProductServiceService {

	private static final Logger logger = Logger.getLogger(OrderProductStepUpServiceImpl.class);

	@Autowired
	private OrderProductStepUpDAO orderProductStepUpDAO;

	@Override
	public OrderProductStepUp save(OrderProductStepUp orderProductStepUp) {
		return orderProductStepUpDAO.save(orderProductStepUp);
	}

}
