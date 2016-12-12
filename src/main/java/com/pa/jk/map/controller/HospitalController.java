package com.pa.jk.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pa.jk.map.bean.Hospital;
import com.pa.jk.map.dao.HospitalDao;
@RestController
@Controller("/hospital")
public class HospitalController {

	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HospitalController.class);

//	@Autowired
//	private HospitalDao hospitalDao;

	@RequestMapping("/save")
	public String greeting() {
		logger.info("hello");
		
		Hospital hospital = new Hospital();
		hospital.setPartnerId(1);
		hospital.setHospitalId(2);
//		hospitalDao.save(hospital);

		return "hello";
	}

}
