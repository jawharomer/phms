package com.joh.phms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.CustomerOrder;

public interface CustomerOrderDAO extends CrudRepository<CustomerOrder, Integer> {

}
