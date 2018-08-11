package com.joh.phms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.CustomerOrder;

public interface CustomerOrderDAO extends CrudRepository<CustomerOrder, Integer>, CustomerOrderDAOExt {
	List<CustomerOrder> findAllByOrderTimeBetween(Date from, Date to);
}
