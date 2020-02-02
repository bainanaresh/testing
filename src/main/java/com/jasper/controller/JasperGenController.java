package com.jasper.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jasper.service.EmployeeJasperRepoService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class JasperGenController {
	
	@Autowired
	private EmployeeJasperRepoService service;
	
	@RequestMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/gen/{format}")
	public @ResponseBody String getReport(@PathVariable("format") String format) throws FileNotFoundException, JRException {
		String str=service.getEmployeeService(format);
		return str;
	}

}
