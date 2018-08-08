package com.joh.phms.service;

import com.joh.phms.model.Vendor;

public interface VendorService {

	Vendor save(Vendor vendor);

	Iterable<Vendor> findAll();

}
