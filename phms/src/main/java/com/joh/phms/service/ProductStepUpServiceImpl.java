package com.joh.phms.service;

import java.util.Date;
import java.util.List;

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
		productStepUp.setSoldQuantity(0);
		return productStepUpDAO.save(productStepUp);
	}

	@Override
	public List<ProductStepUp> findAllByTimeBetween(Date from, Date to) {
		// return productStepUpDAO.findAllByTimeBetween(from, to);
		return null;
	}

	@Override
	public List<ProductStepUp> findAllByExpirationDateLessThanEqual(Date to) {
		return productStepUpDAO.findAllByExpirationDateLessThanEqualOrderByExpirationDate(to);
	}

	@Override
	public void delete(int id) {
		productStepUpDAO.delete(id);
	}
}
