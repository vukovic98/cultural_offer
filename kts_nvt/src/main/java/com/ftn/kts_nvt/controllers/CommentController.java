package com.ftn.kts_nvt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.services.CommentService;

@RestController
@RequestMapping("/kts-nvt")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
}
