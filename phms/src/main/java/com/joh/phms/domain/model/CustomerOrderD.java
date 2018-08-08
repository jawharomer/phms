package com.joh.phms.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

public class CustomerOrderD {

	private Integer customerOrderId;

	@NotBlank(message = "customer Name is bank why")
	private String customerName;
	private Integer doctorId;

	@Valid
	private List<CustomerOrderDetailD> customerOrderDetailDs = new ArrayList<>();

	public Integer getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(Integer customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public List<CustomerOrderDetailD> getCustomerOrderDetailDs() {
		return customerOrderDetailDs;
	}

	public void setCustomerOrderDetailDs(List<CustomerOrderDetailD> customerOrderDetailDs) {
		this.customerOrderDetailDs = customerOrderDetailDs;
	}

	@Override
	public String toString() {
		return "CustomerOrderD [customerOrderId=" + customerOrderId + ", customerName=" + customerName + ", doctorId="
				+ doctorId + ", customerOrderDetailDs=" + customerOrderDetailDs + "]";
	}

}
