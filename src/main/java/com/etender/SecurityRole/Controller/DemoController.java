package com.etender.SecurityRole.Controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etender.SecurityRole.Entity.TenderDepartmentEntity;

import com.etender.SecurityRole.Repository.RoleRepository;
import com.etender.SecurityRole.Repository.TenderDepartmentRepository;
import com.etender.SecurityRole.Repository.TenderMain1Repository;
import com.etender.SecurityRole.Repository.UserRepository;
import com.etender.SecurityRole.Services.DownloadService;
import com.etender.SecurityRole.Services.MyHelper;
import com.etender.SecurityRole.Services.TenderDepartmentService;

import com.etender.SecurityRole.Services.UserService;



@Controller

@RequestMapping("")
public class DemoController {
	int i=34567854;
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	// create a mapping for "/hello"

	

	private TenderDepartmentService tenderDepartmentService;

//	
//	@Autowired
//	private ServletContext servletContext;
	
	@Autowired TenderDepartmentRepository tenderDepartmentRepository;
	
	
	@Autowired
	TenderMain1Repository tenderMain1Repository;
	 

	
	
	/*
	 * public DemoController (UserService userService, UserRepository
	 * userRepostirory, RoleRepository roleRepository) {
	 * 
	 * this.userRepository = userRepository; this.userService = userService;
	 * 
	 * }
	 */
	
	
	
	@GetMapping("/index")
	public String Indexs(Model theModel,@RequestParam(defaultValue="0") int page) throws Exception {
		
	theModel.addAttribute("allTenderMain",tenderMain1Repository.findByLastDateOfSubmissionGreaterThan(getTodayEpoch(),PageRequest.of(page,3)));
	
//	theModel.addAttribute("allTenderMain",tenderMain1Repository.findAllByOrderByIssueDateAsc(PageRequest.of(page,5)));
		
	// theModel.addAttribute("allTenderMain",tenderMain1Repository.findAll(PageRequest.of(page,4)));
		theModel.addAttribute("departments",tenderDepartmentRepository.findAll());
		
		theModel.addAttribute("currentPage", page);
		
	
		
		
		return "index";
	}
	
	@GetMapping("/contactus")
	public String sayHello() {
		
		
		
		return "views/contact-us";
	}
	
	@GetMapping("/login") 
	public String showLoginForm() {
		
		return "login";
		
		/* return "views/Login1"; */ 
	}
	


	
	
	@GetMapping("/error") 
	public String showError() {
		
		return "views/error"; 
	}
	
	
	
	
	
public Integer getTodayEpoch() throws ParseException {
		
		//Integer epochTimeCurrent=0;
		Integer epochTimeCurrent=0;

		
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd,MMM yyyy HH:mm:ss "); //"dd,MMM yyyy HH:mm:ss.SSS zzz
	
		String currentTime = simpleDateFormat.format(today);
	
		System.out.println("current:"+currentTime);
		Date date = simpleDateFormat.parse(currentTime);
		System.out.println("date:"+date);
	
		//epochTimeCurrent =(int) ( date.getTime()) /1000;// I DIVIDE BY 1000 BECAUSE I WANT TO REMOVE THE MILLI SECONDS. THE MILLI SECONDS IS PRESENT HERE BUT NOT PRESENT IN THE DATA BASE. SO COMPARING IS DIFFICULT
		epochTimeCurrent = (int) ((date.getTime()) /1000);// I DIVIDE BY 1000 BECAUSE I WANT TO REMOVE THE MILLI SECONDS. THE MILLI SECONDS IS PRESENT HERE BUT NOT PRESENT IN THE DATA BASE. SO COMPARING IS DIFFICULT

		System.out.println("cuepochTimeCurret:"+epochTimeCurrent);

		return epochTimeCurrent;
	}
}








