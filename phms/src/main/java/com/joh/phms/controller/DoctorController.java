package com.joh.phms.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joh.phms.domain.model.DoctorCustomerOrderD;
import com.joh.phms.model.Doctor;
import com.joh.phms.service.DoctorService;
import com.joh.phms.service.ReportService;
import com.joh.phms.validator.DoctorValidator.ValidationForEdit;

@Controller
@RequestMapping(path = "/doctors")
public class DoctorController {

	private static final Logger logger = Logger.getLogger(CustomerOrderController.class);

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ReportService reportService;

	@GetMapping()
	public String getAllDoctors(Model model) {
		logger.info("getAllDoctors->fired");

		Iterable<Doctor> doctors = doctorService.findAll();

		logger.info("doctors=" + doctors);
		model.addAttribute("doctors", doctors);

		return "doctors";

	}

	@GetMapping(path = "/add")
	private String getAddingDoctor(Model model) {
		logger.info("getAddingDoctor->fired");

		model.addAttribute("doctor", new Doctor());

		return "doctor/addDoctor";
	}

	@PostMapping(path = "/add")
	private String addDoctor(@ModelAttribute @Valid Doctor doctor, BindingResult result, Model model) {

		logger.info("addDoctor->fired");

		logger.info("error=" + result.getAllErrors());
		if (result.hasErrors()) {
			model.addAttribute("product", doctor);
			return "doctor/addDoctor";
		} else {
			doctorService.save(doctor);

			return "success";
		}
	}

	@PostMapping(path = "/delete/{id}")
	private String deleteDoctor(@PathVariable int id) {
		logger.info("deleteDoctor->fired");
		doctorService.delete(id);
		return "success";
	}

	@GetMapping(path = "/edit/{id}")
	private String editingDoctor(@PathVariable int id, Model model) {
		logger.info("editingDoctor->fired");
		logger.info("id=" + id);
		Doctor doctor = doctorService.findOne(id);
		logger.info("doctor=" + doctor);

		model.addAttribute("doctor", doctor);

		return "doctor/editDoctor";
	}

	@PostMapping(path = "/update")
	private String updateDoctor(@ModelAttribute @Validated(ValidationForEdit.class) Doctor doctor,
			BindingResult result) {
		logger.info("updateDoctor->fired");

		logger.info("doctor=" + doctor);

		logger.info("errors=" + result.getAllErrors());
		if (result.hasErrors()) {
			return "doctor/editDoctor";
		} else {
			doctorService.update(doctor);
			return "success";
		}
	}

	@GetMapping(path = "/{id}/customerOrders")
	private String getDoctorCustomerOrders(@PathVariable int id,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getDoctorCustomerOrders->fired");

		logger.info("id=" + id);
		List<DoctorCustomerOrderD> doctorCustomerOrderDs = reportService.findDoctorCustomerOrder(id, from, to);
		logger.info("doctorCustomerOrderDs=" + doctorCustomerOrderDs);

		model.addAttribute("doctorId", id);
		model.addAttribute("doctorCustomerOrderDs", doctorCustomerOrderDs);

		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "doctorCustomerOrders";
	}

}
