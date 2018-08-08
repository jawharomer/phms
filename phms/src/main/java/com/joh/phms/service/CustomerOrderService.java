package com.joh.phms.service;

import com.joh.phms.model.CustomerOrder;

public interface CustomerOrderService {

	CustomerOrder save(CustomerOrder customerOrder);

	CustomerOrder findOne(int id);

}
