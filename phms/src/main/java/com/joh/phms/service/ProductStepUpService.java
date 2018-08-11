package com.joh.phms.service;

import java.util.Date;
import java.util.List;

import com.joh.phms.model.ProductStepUp;

public interface ProductStepUpService {

	ProductStepUp save(ProductStepUp productStepUp);

	List<ProductStepUp> findAllByTimeBetween(Date from, Date to);

	void delete(int id);

}
