package com.joh.phms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.Product;
import com.joh.phms.model.Vendor;

public interface VendorDAO extends CrudRepository<Vendor, Integer> {

}
