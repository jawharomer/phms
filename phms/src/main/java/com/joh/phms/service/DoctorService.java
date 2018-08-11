package com.joh.phms.service;

import com.joh.phms.model.Doctor;

public interface DoctorService {

	Iterable<Doctor> findAll();

	Doctor save(Doctor doctor);

	void delete(int id);

	Doctor findOne(int id);

	Doctor update(Doctor doctor);

}
