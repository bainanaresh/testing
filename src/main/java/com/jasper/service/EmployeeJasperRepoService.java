package com.jasper.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.jasper.entity.Employee;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeJasperRepoService {
	
	public String getEmployeeService(String format) throws FileNotFoundException, JRException{
		String path="C:\\Users\\BAINA NARESH\\Desktop\\aaaaaa";
		
		List<Employee> empList=new ArrayList<>();
		for(int i=1;i<=45;i++) {
			Employee e=new Employee();
			e.setEid(i);
			e.setEname("name :"+i);
			e.setSalary(i);
			e.setDesignation("designation :"+i);
			e.setAddress("address :"+i);
			empList.add(e);
		}
		
		File file=ResourceUtils.getFile("classpath:employees.jrxml");
		System.out.println("********"+file.getAbsolutePath());
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource=new JRBeanCollectionDataSource(empList);
		Map<String,Object> params=new HashMap<>();
		params.put("Created By","Naresh Baina");
		JasperPrint jasperprint=JasperFillManager.fillReport(jasperReport, params, datasource);
		if(format.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperprint,path+"\\employees.pdf");
		}
		else if(format.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperprint,path+"\\employees.html");
		}
		else if(format.equalsIgnoreCase("xml")) {
			JasperExportManager.exportReportToXmlFile(jasperprint,path+"\\employees.xml",false);
		}
		return "generated reported path "+path; 
		}

}
