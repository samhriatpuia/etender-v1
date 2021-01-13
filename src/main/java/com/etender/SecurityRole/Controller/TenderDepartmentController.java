package com.etender.SecurityRole.Controller;
/*
 * 	Created By: Lalthanpuia Chhangte
	Date 	:  27 July, 19
	Description:	TenderDepartmentController
 * 
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etender.SecurityRole.Entity.TenderDepartmentEntity;
import com.etender.SecurityRole.Repository.TenderDepartmentRepository;
import com.etender.SecurityRole.Services.MyHelper;
import com.etender.SecurityRole.Services.TenderDepartmentService;




@Controller				
@RequestMapping("/tenderDepartment")
public class TenderDepartmentController {

	//private TenderDepartmentService tenderDepartmentService;
	
	@Autowired
	private TenderDepartmentRepository tenderDepartmentRepository;
	
	//WE HAVE TO DECLARE THE JAVAMAILSENDER IN THE CONTROLLER AND PASS TO THE sendMail() function
	@Autowired
	private JavaMailSender sender;
	
//	public TenderDepartmentController(TenderDepartmentService theTenderDepartmentService) {
//		this.tenderDepartmentService = theTenderDepartmentService;
//	}

	@GetMapping("/showAllDept")
	public String getAllTenderDepartment(Model theModel) {
			
			List<TenderDepartmentEntity> thetenderDept = tenderDepartmentRepository.findAllByOrderByNameAsc();
			
			theModel.addAttribute("allTenderDepartment", thetenderDept);
			theModel.addAttribute("sort", "Sort in Descending");
			theModel.addAttribute("link", "showAllDeptDesc");

			return "/views/departmentList";
		}
	
	@GetMapping("/showAllDeptDesc")
	public String getAllTenderDepartmentDesc(Model theModel) {

			
			List<TenderDepartmentEntity> thetenderDept = tenderDepartmentRepository.findAllByOrderByNameDesc();
			
			theModel.addAttribute("allTenderDepartment", thetenderDept);
			theModel.addAttribute("sort", "Sort in Ascending");
			theModel.addAttribute("link", "showAllDept");

			return "departmentList";
		}
	
	
	
	@PostMapping("/save")
	public String saveMedicine(@ModelAttribute("allTenderDepartment") TenderDepartmentEntity tenderDepartmentEntity) {
		
		// save the company
		tenderDepartmentRepository.save(tenderDepartmentEntity);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/admin/listDept";
	}
	
	
	
	
	
	


	@GetMapping("/findOne")
	@ResponseBody
	public Optional<TenderDepartmentEntity> findOne(Integer id) {
		

		return tenderDepartmentRepository.findById(id);
				
	}

	
	
	@GetMapping("/delete")
	public String delete(Integer id){
		
		tenderDepartmentRepository.deleteById(id);
		 
		return "redirect:/medicine/list";
	}
	
	
	
	
//	@GetMapping("/archived/{mMonth}/{mYear}")
//	public String archived(Model theModel, @PathVariable String mMonth, @PathVariable String mYear) throws ParseException {     
//	
	
//	@GetMapping("testMail")
//	public String testMail() {
//		
//		MyHelper myHelper = new MyHelper();
//		
//		TenderMainEntity myTenderMain = new TenderMainEntity();
//
//		// sendMail is user define function. we are passing two parameter, myTenderMain object contains
//		// all the data for the mail . Sender is the JavaMailSender object. First we initialize in the controller and pass to the  mail sent function. initialize in th e funtionc is a problem
//		
//		//myHelper.sendMail(myTenderMain, sender);
//
//
//		
//		return "ListOfDepartments";
//	}
//	
	
	

	
}
