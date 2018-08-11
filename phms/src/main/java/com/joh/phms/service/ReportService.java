package com.joh.phms.service;

import java.util.Date;
import java.util.List;

import com.joh.phms.domain.model.DoctorCustomerOrderD;

public interface ReportService {

	List<DoctorCustomerOrderD> findDoctorCustomerOrder(int doctorId, Date from, Date to);

}
