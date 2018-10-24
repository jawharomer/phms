package com.joh.phms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.ProductUnitTypeDAO;
import com.joh.phms.model.ProductUnitType;

@Service
public class ProductUnitTypeServiceImpl implements ProductUnitTypeService {

	@Autowired
	private ProductUnitTypeDAO productUnitTypeDAO;

	@Override
	public Iterable<ProductUnitType> findAll() {
		return productUnitTypeDAO.findAll();
	}

}
