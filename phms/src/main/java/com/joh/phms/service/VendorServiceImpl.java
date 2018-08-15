package com.joh.phms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.VendorDAO;
import com.joh.phms.model.Vendor;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorDAO vendorDAO;

	@Override
	public Iterable<Vendor> findAll() {
		return vendorDAO.findAll();
	}

	@Override
	@Transactional
	public Vendor save(Vendor vendor) {
		return vendorDAO.save(vendor);
	}

	@Override
	@Transactional
	public void delete(int id) {
		vendorDAO.delete(id);
	}

	@Override
	public Vendor findOne(int id) {
		return vendorDAO.findOne(id);
	}

	@Override
	@Transactional
	public Vendor update(Vendor vendor) {
		vendor.setId(vendorDAO.findOne(vendor.getId()).getId());
		return vendorDAO.save(vendor);
	}
}
