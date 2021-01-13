package com.etender.SecurityRole.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.etender.SecurityRole.Entity.TenderMain;
import com.etender.SecurityRole.Entity.TenderSupplierUser;
import com.etender.SecurityRole.Entity.User;
import com.etender.SecurityRole.Repository.TenderDepartmentRepository;
import com.etender.SecurityRole.Repository.TenderSupplierUserRepository;
import com.etender.SecurityRole.Repository.UserRepository;




@Service
public class MyHelper {
//	
//	@Autowired
//	private JavaMailSender sender;
	
//	
//	@Autowired
//	 TenderSupplierUserRepository tenderSupplierUserRepository;
//	
//	@Autowired
//	 UserRepository userRepository;
//	
//	@Autowired
//	static TenderDepartmentRepository tenderDepartmentRepository;
//	
////	
//	public MyHelper(TenderSupplierUserRepository tenderSupplierUserRepository, UserRepository userRepository, TenderDepartmentRepository tenderDepartmentRepository) {
//		
//		this.tenderSupplierUserRepository=tenderSupplierUserRepository;
//		this.userRepository = userRepository;
//		this.tenderDepartmentRepository = tenderDepartmentRepository;
//		
//	}
	
	public MyHelper() {}
//	
//	@Autowired
//	private JavaMailSender sender;
//	
	/*
	 * public void sendMail(TenderMainEntity tenderMain, JavaMailSender senders) {
	 * 
	 * SimpleMailMessage msg = new SimpleMailMessage();
	 * msg.setTo("thanpuia46@gmail.com");
	 * 
	 * msg.setSubject("Testing from Spring Boot");
	 * msg.setText("Hello World \n Spring Boot Email"); senders.send(msg);
	 * 
	 * try {
	 * 
	 * }catch(Exception e) {System.out.println(e);}
	 * 
	 * // MimeMessage message = sender.createMimeMessage(); // MimeMessageHelper
	 * helper = new MimeMessageHelper(message); // // String subject
	 * ="sample subject"; // // String messageBody = "sample body"; // // try { //
	 * helper.setTo("thanpuia46@gmail.com"); // helper.setText(messageBody); //
	 * helper.setSubject(subject); // } catch (MessagingException e) { //
	 * e.printStackTrace(); // return "Error while sending mail .."; // } // //
	 * sender.send(message); //return ; }
	 */
	
	
	
	public int convertDateToEpochInt(String mDate) throws ParseException {
		int convertedDate =0;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String mTime = simpleDateFormat.format(mDate);
		Date date = simpleDateFormat.parse(mTime);
		convertedDate = (int) date.getTime() / 1000;
		
		return convertedDate;
	}
	
	public String sendOTP(String number) {
		final String uri = "https://sms.mizoram.gov.in/api";

	    Random r = new Random();
	    String randomNumber = String.format("%04d", r.nextInt(1001)); //FOUR Digit Random Number Generated 
	    System.out.println(randomNumber);
	    
	    RestTemplate restTemplate = new RestTemplate();
	    
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("api_key", "b53366c91585c976e6173e69f6916b2d");
	    params.put("message", "Your OTP for e-Tender Registration is: "+randomNumber);
	    params.put("number", number);
	    
	    String result = restTemplate.postForObject(uri,params, String.class);
	    
	    System.out.println(result);

	    
	    return randomNumber;
	}
	
	public boolean checkOTP(String userOTP, String generateOTP ) {
		if(userOTP.equals(generateOTP))
			return true;
		else
			return false;
	}
	
	

	// ******SMS STARTS******
	public static String alertSubscriberSms(Integer deptId, TenderMain tenderMain, String deptName, TenderSupplierUserRepository tenderSupplierUserRepository, UserRepository userRepository) {
	//public static String alertSubscriber(TenderMain tenderMain) {
	
		System.out.println("Beginning of the Greats...");
		System.out.println("Dept Id:"+tenderMain.getDepartment());
		ArrayList<Integer> userDeptSubscribeInArray = new ArrayList<>();

		String keyword = tenderMain.getDepartment().toString();
		
		System.out.println(tenderMain.getSubject());
		System.out.println(tenderMain.getAttachment());
		
		//String deptName = tenderDepartmentRepository.findById(tenderMain.getDepartment());
		
		
	//	List<TenderSupplierUser> mTenderSupplierUsers = tenderSupplierUserRepository.findByDepartmentSubscriptionContaining(keyword);
		List<TenderSupplierUser> mAllTenderSupplierUsers = tenderSupplierUserRepository.findAll();
	
		System.out.println("After accessing repo");

		for(int i= 0 ;i<mAllTenderSupplierUsers.size();i++) {

			//SEPARATE THE ATTACHMENT FILE INTO LIST IF THERE ARE MORE THAN ONE
			List<String> userSubscribeDept = Arrays.asList(mAllTenderSupplierUsers.get(i).getDepartmentSubscription().split("\\s*,\\s*"));

			for(int j = 0;j<userSubscribeDept.size();j++) {
					
				//Checking the Dept ID
				if(userSubscribeDept.get(j).equals(keyword)) {
					System.out.println("HIT: "+ mAllTenderSupplierUsers.get(i).getUserId());
					
					userDeptSubscribeInArray.add(mAllTenderSupplierUsers.get(i).getUserId());
				}
			}
		}

		//TODO: SENT MESSAGE TO SUBSCRIBE USER ONE BY ONE
		for(int k = 0; k<userDeptSubscribeInArray.size(); k++) {
			System.out.println("Phone:sdf");

			User mUsers = userRepository.findByUserId(userDeptSubscribeInArray.get(k));
			String phone = mUsers.getUser_phone();
			
			System.out.println("Phone:"+phone);
			String message= "New Tender Release from "+deptName+"\n"+ "Topic: "+ tenderMain.getSubject();
			
			sendSms(message,phone);
		}

		return "index";
	}
	
	public static void sendSms(String message, String phoneNo) {
	    final String uri = "https://sms.mizoram.gov.in/api";

	    RestTemplate restTemplate = new RestTemplate();
	    
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("api_key", "b53366c91585c976e6173e69f6916b2d");
	   // params.put("api_key", "b53366c91585c976e6173e69f6916");

	    params.put("message", message);
	    params.put("number", phoneNo);
	    
	    String result = restTemplate.postForObject(uri,params, String.class);
	    	
	    System.out.println(result);
	}
	// ******SMS ENDS******
	
	
	
	
	
	// ******EMAIL STARTS******
		public static String alertSubscriberEmail(Integer deptId, TenderMain tenderMain, String deptName, 
									TenderSupplierUserRepository tenderSupplierUserRepository, UserRepository userRepository, JavaMailSender senders) {
			
			ArrayList<Integer> userDeptSubscribeInArray = new ArrayList<>();
			String keyword = tenderMain.getDepartment().toString();

			
			List<TenderSupplierUser> mAllTenderSupplierUsers = tenderSupplierUserRepository.findAll();

			System.out.println("After accessing repo");

			for(int i= 0 ;i<mAllTenderSupplierUsers.size();i++) {

				//SEPARATE THE ATTACHMENT FILE INTO LIST IF THERE ARE MORE THAN ONE
				List<String> userSubscribeDept = Arrays.asList(mAllTenderSupplierUsers.get(i).getDepartmentSubscription().split("\\s*,\\s*"));

				for(int j = 0;j<userSubscribeDept.size();j++) {
						
					//Checking the Dept ID
					if(userSubscribeDept.get(j).equals(keyword)) {
						System.out.println("HIT: "+ mAllTenderSupplierUsers.get(i).getUserId());
						
						userDeptSubscribeInArray.add(mAllTenderSupplierUsers.get(i).getUserId());
				
					}
				}
			}
			
			//TODO: SENT EMAIL TO SUBSCRIBE USER ONE BY ONE
			for(int k = 0; k<userDeptSubscribeInArray.size(); k++) {
				System.out.println("EMAIL");

				User mUsers = userRepository.findByUserId(userDeptSubscribeInArray.get(k));
				String email = mUsers.getEmail();
				
				System.out.println("Email:"+email);
				
				//sendSms(message,phone);
				sendMail(senders, email, tenderMain, deptName);
			}
			
			return "index";
		}
		
		public static void sendMail(JavaMailSender senders, String mEmail, TenderMain tenderMain, String deptName) {
			
	        SimpleMailMessage msg = new SimpleMailMessage();
	        
			String messageBody= "New Tender Release from "+deptName+"\n"+ "Topic: "+ tenderMain.getDetail();
			String messageSubject = "New Tender for "+deptName;
	        
	        
	        msg.setTo(mEmail);

	        msg.setSubject(messageSubject);
	        msg.setText(messageBody);
	        senders.send(msg);
	        System.out.println("....after send");

	        try {

	        }catch(Exception e) {System.out.println(e);}

		}
		
		// ******EMAIL ENDS******
		
	
	
}





