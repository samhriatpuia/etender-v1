package com.etender.SecurityRole.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etender.SecurityRole.Config.RecaptchaService;
import com.etender.SecurityRole.Entity.TenderDepartmentUser;
import com.etender.SecurityRole.Entity.TenderSupplierUser;
import com.etender.SecurityRole.Entity.User;
import com.etender.SecurityRole.Repository.DistrictRepository;
import com.etender.SecurityRole.Repository.RoleRepository;
import com.etender.SecurityRole.Repository.TenderDepartmentRepository;
import com.etender.SecurityRole.Repository.TenderDepartmentUserRepository;
import com.etender.SecurityRole.Repository.TenderSupplierUserRepository;
import com.etender.SecurityRole.Repository.UserRepository;
import com.etender.SecurityRole.Services.MyHelper;
import com.etender.SecurityRole.Services.UserService;





@Controller
@RequestMapping("/")
public class RegisterController {
	
	long epoch = System.currentTimeMillis()/1000;
	int i = (int) epoch;

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MyHelper myHelper;
	
	@Autowired
	TenderDepartmentRepository tenderDepartmentRepository;
	
	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired 
	private UserRepository userRepository;
	

	@Autowired
	private TenderDepartmentUserRepository tenderDepartmentUserRepository;
	

	@Autowired
	private TenderSupplierUserRepository tenderSupplierUserRepository;
	
	@Autowired
	RecaptchaService captchaService;
	
	User user=new User();
	
	TenderDepartmentUser duser= new TenderDepartmentUser();
	
	TenderSupplierUser suser= new TenderSupplierUser();
	
	
	
	String otpcode;
	
	
	
//	
//	@Autowired
//	RegisterController (TenderDepartmentUser tenderDepartmentUser, TenderDepartmentUserRepository tenderDepartmentUserRepository,TenderSupplierUser tenderSupplierUser,TenderSupplierUserRepository tenderSupplierUserRepository){
//	this.tenderDepartmentUserRepository=tenderDepartmentUserRepository;
//	this.tenderSupplierUserRepository=tenderSupplierUserRepository;
//	}
//		
	//User Supplier Registration Page
	
	@GetMapping("/supregister")
	public String registerForm(Model model) {

		model.addAttribute("user", new User());
		model.addAttribute("departments",tenderDepartmentRepository.findAll());
		model.addAttribute("districts",districtRepository.findAll());
		
		return "views/RegisterSupplier";
	}
	
	//supplier Registation post
	
	@PostMapping("/supregister")
	 public String supplierUser(@Valid User user, BindingResult bindingResult, Model model) {
//    public String supplierUser(@Valid User user, BindingResult bindingResult, Model model,@RequestParam(name="g-recaptcha-response") String recaptchaResponse,
//			HttpServletRequest request) {
		
		
//		GoogleRecapcha
//		
//		String ip = request.getRemoteAddr();
//		String captchaVerifyMessage = 
//			captchaService.verifyRecaptcha(ip, recaptchaResponse);
//		
//		if ( StringUtils.isNotEmpty(captchaVerifyMessage)) {
//			Map<String, Object> response = new HashMap<>();
//			response.put("message", captchaVerifyMessage);
//			System.out.println("INDIDE IF");
//			
//			return "views/RegisterSupplier";
//		}
		
		
		
		//h o n e y p o t
        if(user.getMsegsHoney() != null && !user.getMsegsHoney().isEmpty()) {
            // IF IT IS TRUE . THEN SPAM
            System.out.println("This is a spam!!");
            return "views/RegisterSupplier";
        }
		
		//userRepository.save(user);
		System.out.println("OUT SIDE IF");
		
		model.addAttribute("departments",tenderDepartmentRepository.findAll());
		model.addAttribute("districts",districtRepository.findAll());
		
		
		
		if(bindingResult.hasErrors()) {
			return "views/RegisterSupplier";
		}
		if(userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist",true);

			return "views/RegisterSupplier";

		}
		
		user.setUser_created_date(i);
		user.setUser_last_logged(i);
		user.setUser_login(user.getEmail());
		user.setUser_role("SUP");
		
		this.user=user;
	
	//	userService.createSupplier(user);
		
		
		
		System.out.println(user.getDepartmentSubscribtion());
		
		
		//TenderSupplierUser Set  
		
		
		TenderSupplierUser suser= new TenderSupplierUser();
		
		//suser.setUser_id(user.getUser_id());
		suser.setDepartmentSubscription(user.getDepartmentSubscribtion());
		
		this.suser=suser;
		
		
	//	tenderSupplierUserRepository.save(suser);
		
		
		//OTP generate na
		
		String otpcode=myHelper.sendOTP(user.getUser_phone()); 
		
		this.otpcode=otpcode;
		System.out.println(this.otpcode);
		
		
				return "views/RegistrationOTPSupplier";

	}
	
	
	//User Department Registration Page
		@GetMapping("/deptregister")
		public String deptform(Model model) {

			model.addAttribute("user", new User());
			model.addAttribute("departments",tenderDepartmentRepository.findAll());
			model.addAttribute("districts",districtRepository.findAll());
			return "views/RegisterDepartment";
			
		}
	
	@PostMapping("/deptregister")
    public String deptUser(@Valid User user, BindingResult bindingResult, Model model) {
		
		
		
		
		
		
		
		model.addAttribute("departments",tenderDepartmentRepository.findAll());
		model.addAttribute("districts",districtRepository.findAll());
		
		
		//h o n e y p o t
        if(user.getMsegsHoney() != null && !user.getMsegsHoney().isEmpty()) {
            // IF IT IS TRUE . THEN SPAM
            System.out.println("This is a spam!!");
            return "views/RegisterDepartment";
        }
		
		
		if(bindingResult.hasErrors()) {
			return "views/RegisterDepartment";
		}
		if(userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist",true);

			return "views/RegisterDepartment";

		}
		
		
		user.setUser_created_date(i);
		user.setUser_last_logged(i);
		user.setUser_login(user.getEmail());
		user.setUser_role("DEPT");
		
		//user.setUser_id(user.getEmail());
		
		
		this.user=user;
	
		//userService.createDept(user);
		
		
		//TenderDepartmentUser Set  
		
		
				TenderDepartmentUser duser= new TenderDepartmentUser();
				
				duser.setUser_id(user.getUserId());
				
				duser.setDepartment_id(user.getDepartment_id());
				
				duser.setDesignation(user.getDesignation());
				
				duser.setDistrict(user.getDistrict());
				
				duser.setApproved(0);
				
				
				this.duser=duser;
				
			//	tenderDepartmentUserRepository.save(duser);
				
				
				
				//OTP generate na
				
				String otpcode=myHelper.sendOTP(user.getUser_phone()); 
				
				this.otpcode=otpcode;
				System.out.println(this.otpcode);
				
		
				return "views/RegistrationOTP";

	}
	
	
	
	@GetMapping("/otpCompare")
    public String otpCompare(@RequestParam String otp, Model model) {
		
		
		System.out.println(otp);
		System.out.println(this.otpcode);
		
		
		if(this.otpcode.equals(otp))
			
		{
			

			userService.createDept(this.user);

			this.duser.setUser_id(user.getUserId());
			tenderDepartmentUserRepository.save(this.duser);
			
			
			return "views/registrationSuccess";
			
			
		}
		
		
		else
			
		model.addAttribute("error",true);
		
		
		return "views/RegistrationOTP";
		
		
		
	}
	
	// Suppler Register OTP callna
	
	@GetMapping("/otpComparesupplier")
    public String otpCompareSupplier(@RequestParam String otp, Model model) {
		
		
		System.out.println(otp);
		System.out.println(this.otpcode);
		
		
		if(this.otpcode.equals(otp))
			
		{
			

			userService.createDept(this.user);

			suser.setUserId(user.getUserId());
			tenderSupplierUserRepository.save(this.suser);
			
			
			return "views/registrationSuccess";
			
			
		}
		
		
		else
			
		model.addAttribute("error",true);
		
		
		return "views/RegistrationOTPSupplier";
		
		
		
	}
	

}


