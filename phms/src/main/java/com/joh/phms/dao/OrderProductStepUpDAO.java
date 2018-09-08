package com.joh.phms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.OrderProductStepUp;

public interface OrderProductStepUpDAO extends CrudRepository<OrderProductStepUp, Integer> {

	List<OrderProductStepUp> findAllByOrderTimeBetween(Date from, Date to);
}
