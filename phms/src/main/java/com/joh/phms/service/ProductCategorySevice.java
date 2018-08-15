package com.joh.phms.service;

import com.joh.phms.model.ProductCategory;

public interface ProductCategorySevice {


	Iterable<ProductCategory> findAll();

	ProductCategory save(ProductCategory productCategory);

	void delete(int id);

	ProductCategory findOne(int id);

	ProductCategory update(ProductCategory productCategory);

}
