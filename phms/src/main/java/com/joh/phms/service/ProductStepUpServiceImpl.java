package com.joh.phms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.ProductStepUpDAO;
import com.joh.phms.model.ProductStepUp;

@Service
public class ProductStepUpServiceImpl implements ProductStepUpService {

	@Autowired
	private ProductStepUpDAO productStepUpDAO;

	@Override
	public ProductStepUp save(ProductStepUp productStepUp) {
		return productStepUpDAO.save(productStepUp);
	}
}
