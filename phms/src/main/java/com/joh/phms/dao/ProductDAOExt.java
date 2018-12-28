package com.joh.phms.dao;

import java.util.List;

import com.joh.phms.domain.model.ProductD;

public interface ProductDAOExt {

	List<ProductD> findStock();


	ProductD findProductByCode(String productCode);


	ProductD findProductByProductStepUpId(int productStepUpId);
}
