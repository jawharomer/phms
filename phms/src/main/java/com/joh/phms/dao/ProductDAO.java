package com.joh.phms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.Product;

public interface ProductDAO extends CrudRepository<Product, Integer>,ProductDAOExt{

}
