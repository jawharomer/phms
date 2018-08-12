package com.joh.phms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.ProductDAO;
import com.joh.phms.domain.model.ProductD;
import com.joh.phms.exception.ItemExistsException;
import com.joh.phms.model.Product;

@Service
public class ProductServiceImpl implements ProductSevice {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public Product findOne(int id) {
		return productDAO.findOne(id);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		try {
			return productDAO.save(product);
		} catch (DataIntegrityViolationException e) {
			throw new ItemExistsException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public List<ProductD> findStock() {
		return productDAO.findStock();
	}

	@Override
	@Transactional
	public void delete(int id) {
		productDAO.delete(id);
	}

	@Override
	@Transactional
	public Product update(Product product) {
		// This line will check this student is exit
		// then it will update it
		productDAO.findOne(product.getId());
		return productDAO.save(product);
	}

	@Override
	public ProductD findProductByCode(String code) {
		return productDAO.findProductByCode(code);
	}
}
