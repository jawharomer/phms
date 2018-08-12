package com.joh.phms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.phms.dao.ReportDAO;
import com.joh.phms.domain.model.DoctorCustomerOrderD;
import com.joh.phms.domain.model.NotificationD;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDAO reportDAO;

	@Override
	public List<DoctorCustomerOrderD> findDoctorCustomerOrder(int doctorId,Date from, Date to) {
		return reportDAO.findDoctorCustomerOrder(doctorId,from, to);
	}
	
	
	@Override
	public List<NotificationD> findAdminNotifications() {
		return reportDAO.findAdminNotifications();
	}
}
