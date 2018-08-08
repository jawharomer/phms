package com.joh.phms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.DoctorDAO;
import com.joh.phms.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDAO doctorDAO;
	
	
	@Override
	public Iterable<Doctor> findAll(){
		return doctorDAO.findAll();
	}

	
}
