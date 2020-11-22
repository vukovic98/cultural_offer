package com.ftn.kts_nvt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.services.MonumentService;

@RestController
@RequestMapping("/monument")
public class MonumentController {

	@Autowired
	private MonumentService monumentService;
}
