package com.jnana.ReportApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jnana.ReportApp.dto.SearchDTO;
import com.jnana.ReportApp.entity.CitizenPlan;
import com.jnana.ReportApp.service.ReportService;
import java.util.List;


@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@PostMapping("/search")
	public String searchhandler(@ModelAttribute("search") SearchDTO search, Model model) {
		
		System.out.println(search);
		System.out.println("--------");
		List<CitizenPlan> plans = service.search(search);
		model.addAttribute("plans",plans);
		
		init(model);
		
		
		return "pages/index";
	}
	
	
	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search" ,new SearchDTO());
		init(model);
		
		return "pages/index";
	}


	private void init(Model model) {
//		model.addAttribute("search" ,new SearchDTO());
		System.out.println(model);
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatuses());
	}
}
