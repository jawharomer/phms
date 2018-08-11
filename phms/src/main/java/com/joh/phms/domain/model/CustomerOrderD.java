package com.joh.phms.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.joh.phms.validator.CustomerOrderDValidator.ValidationForEdit;

public class CustomerOrderD {

	@NotNull(groups = { ValidationForEdit.class }, message = "Customer order id is null")
	private Integer customerOrderId;

	@NotBlank(message = "customer Name is bank why")
	private String customerName;
	private Integer doctorId;
	private Integer discountId;

	@Size(min = 1, message = "no  cusomter order detail is added")
	@Valid()
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

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}
	
	

	@Override
	public String toString() {
		return "CustomerOrderD [customerOrderId=" + customerOrderId + ", customerName=" + customerName + ", doctorId="
				+ doctorId + ", discountId=" + discountId + ", customerOrderDetailDs=" + customerOrderDetailDs + "]";
	}

}
