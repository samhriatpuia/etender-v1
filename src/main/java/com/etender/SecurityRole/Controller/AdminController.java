package com.etender.SecurityRole.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etender.SecurityRole.Entity.TenderDepartmentEntity;
import com.etender.SecurityRole.Repository.DownloadRepository;
import com.etender.SecurityRole.Repository.TenderDepartmentRepository;
import com.etender.SecurityRole.Repository.UserRepository;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
	

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TenderDepartmentRepository tenderDepartmentRepository;
	
	@Autowired
	DownloadRepository downloadRepository;
	
	
	public AdminController(TenderDepartmentRepository tenderDepartmentReepository)
	{
		
		this.tenderDepartmentRepository=tenderDepartmentRepository;
	
	}
	
	
	
	
	
	@GetMapping("/listDept")
	public String ListDept(Model theModel) {
		
		
	
		theModel.addAttribute("departments",tenderDepartmentRepository.findAll());
		
		
		
		
		return "views/ListDepartment";
	}
	@GetMapping("/showAllDept")
	public String getAllTenderDepartment(Model theModel,@RequestParam(defaultValue="0") int page) {
			
			Page<TenderDepartmentEntity> dept= tenderDepartmentRepository.findAll(PageRequest.of(page,10));
			
			theModel.addAttribute("allTenderDepartment",dept);
			theModel.addAttribute("sort", "Sort in Descending");
			theModel.addAttribute("link", "showAllDeptDesc");
			theModel.addAttribute("currentPage", page);



			return "views/departmentList";

		
		}
	
	
	
	@GetMapping("/addDept")
	public String AddDept(Model theModel) {
		
		
	
		theModel.addAttribute("departments",tenderDepartmentRepository.findAll());
		
		
		
		
		return "views/addTenderDept";
	}
	
	
	
	
	
	
	@GetMapping("/addDeptUser")
	public String AddDeptUser(Model theModel) {
		
		
	
		theModel.addAttribute("departments",tenderDepartmentRepository.findAll());
		
		
		
		return "views/addTenderDepartmentUser";
	}
	
	
	
	@GetMapping("/userList")
	public String Userlist(Model theModel) {
		
		
	
		theModel.addAttribute("departments",tenderDepartmentRepository.findAll());
		theModel.addAttribute("user",userRepository.findAll());
		
		
		
		return "views/userList";
	}
	
	
	@GetMapping("/downloadList")
	public String Downloadlist(Model theModel) {
		
		
	
		theModel.addAttribute("departments",tenderDepartmentRepository.findAll());
		theModel.addAttribute("download",downloadRepository.findAll());
		
		//
		
		return "views/downloadList";
	}
	

 
}
