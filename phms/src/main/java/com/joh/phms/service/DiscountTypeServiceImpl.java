package com.joh.phms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.DiscountTypeDAO;
import com.joh.phms.model.DiscountType;

@Service
public class DiscountTypeServiceImpl implements DiscountTypeService {

	@Autowired
	private DiscountTypeDAO discountTypeDAO;

	@Override
	public Iterable<DiscountType> findAll() {
		return discountTypeDAO.findAll();
	}

}
