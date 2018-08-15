package com.joh.phms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.ProductCategoryDAO;
import com.joh.phms.exception.ItemExistsException;
import com.joh.phms.model.ProductCategory;

@Service
public class ProductCategoryServiceImpl implements ProductCategorySevice {

	@Autowired
	private ProductCategoryDAO productCategoryDAO;

	@Override
	public Iterable<ProductCategory> findAll() {
		return productCategoryDAO.findAll();
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		productCategory.setId(0);// JOH-Fix Bug
		try {
			return productCategoryDAO.save(productCategory);
		} catch (DataIntegrityViolationException e) {
			throw new ItemExistsException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) {
		productCategoryDAO.delete(id);
	}

	@Override
	public ProductCategory findOne(int id) {
		return productCategoryDAO.findOne(id);
	}

	@Override
	@Transactional
	public ProductCategory update(ProductCategory productCategory) {
		productCategory.setId(productCategoryDAO.findOne(productCategory.getId()).getId());
		return productCategoryDAO.save(productCategory);
	}

}
