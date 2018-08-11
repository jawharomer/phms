package com.joh.phms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.joh.phms.validator.DoctorValidator.ValidationForEdit;

@Entity
@Table(name = "DOCTORS")
public class Doctor {

	@NotNull(groups = { ValidationForEdit.class },message="doctor id is null")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_DOCTOR")
	private Integer id;

	@NotBlank(message = "full name is blank")
	@Column(name = "FULL_NAME")
	private String fullName;

	@NotBlank(message = "Phone is balnk")
	@Column(name = "PHONE")
	private String phone;

	@NotNull(message = "Profit is null")
	@Max(value = 1, message = "profit can not be greater than 1")
	@Min(value = 0, message = "profit can not be greater lesson than 0")
	@Column(name = "PROFIT")
	private Double profit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", profit=" + profit + "]";
	}

}
