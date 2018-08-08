package com.joh.phms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.phms.model.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

}
