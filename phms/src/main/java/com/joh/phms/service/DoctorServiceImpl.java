package com.joh.phms.service;

import javax.transaction.Transactional;

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
	
	@Override
	public Doctor save(Doctor doctor){
		return doctorDAO.save(doctor);
	}
	
	
	@Override
	@Transactional
	public void delete(int id){
		 doctorDAO.delete(id);
	}

	
	@Override
	public Doctor findOne(int id){
		return doctorDAO.findOne(id);
	}
	
	
	@Override
	@Transactional
	public Doctor update(Doctor doctor){
		 doctorDAO.findOne(doctor.getId());
		 return doctorDAO.save(doctor);
	}


	
}
