package com.joh.phms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.joh.phms.validator.OrderProductStepUpValidator;
import com.joh.phms.validator.VendorValidator.ValidationForEdit;

@Entity
@Table(name = "VENDORS")
public class Vendor {

	@NotNull(groups = { ValidationForEdit.class, OrderProductStepUpValidator.Insert.class }, message = "id is null")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_VENDOR")
	private int id;

	@NotBlank(message = "Full Name is blank")
	@Column(name = "FULL_NAME")
	private String fullName;

	@NotBlank(message = "Phone is blank")
	@Column(name = "PHONE")
	private String phone;

	@NotBlank(message = "Address is blank")
	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "NOTE")
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", address=" + address + ", note="
				+ note + "]";
	}

}
