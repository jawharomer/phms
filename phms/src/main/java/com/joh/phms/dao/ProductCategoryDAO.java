package com.joh.phms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.ProductCategory;

public interface ProductCategoryDAO extends CrudRepository<ProductCategory, Integer> {

}
