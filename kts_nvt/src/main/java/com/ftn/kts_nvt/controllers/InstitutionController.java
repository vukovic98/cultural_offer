package com.ftn.kts_nvt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.services.InstitutionService;

@RestController
@RequestMapping("/institution")
public class InstitutionController {

	@Autowired
	private InstitutionService institutionService;
}
