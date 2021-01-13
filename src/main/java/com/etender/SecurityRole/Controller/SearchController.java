package com.etender.SecurityRole.Controller;

//Created by Lalthanpuia Chhangte

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.etender.SecurityRole.Entity.TenderDepartmentEntity;
import com.etender.SecurityRole.Entity.TenderMain;

import com.etender.SecurityRole.Repository.TenderDepartmentRepository;
import com.etender.SecurityRole.Repository.TenderMain1Repository;

@Controller
@RequestMapping("/tenderMain1")
public class SearchController {
	
//	private DownloadService downloadService;
//	private TenderDepartmentService tenderDepartmentService;
	
	@Autowired
	private TenderMain1Repository tenderMain1Repository;
	
	@Autowired
	private TenderDepartmentRepository tenderDepartmentRepository;
	
	
	  public SearchController (TenderMain1Repository tenderMain1Repository,TenderDepartmentRepository tenderDepartmentRepository) {
		  
		  this.tenderDepartmentRepository=tenderDepartmentRepository;
	  
		  this.tenderMain1Repository=tenderMain1Repository;

	  }

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(@RequestParam("general") String general,
			 						@RequestParam("department") String dept,
			 						Model theModel,@RequestParam(defaultValue="0") int page) throws Exception {  //SearchHelper search){

		System.out.println("general:"+general +" department:"+dept);
		
		theModel.addAttribute("showAllValid","Show all valid");
		
		theModel.addAttribute("general",general);
		theModel.addAttribute("department",dept);
		
		//IF BOTH ARE EMPTY ALL VALID IS SHOWN SO, THE "show all valid" is not necessary
		if(general.equals("") && dept.equals("")) {
			theModel.addAttribute("showAllValid","");
		}
		
		//Search by only DEPT name. 
		if(general.equals("") && !dept.equals("")) {
			
			//TenderDepartmentEntity tenderDept = tenderDepartmentService.findByName(dept);
			//int deptId = tenderDept.getId();
			 
			Integer deptId = Integer.parseInt(dept);
			//List<TenderMain> tenderByDept = tenderMainRepository.findByDepartmentAndLastDateOfSubmissionGreaterThan(deptId, getTodayEpoch(),PageRequest.of(page, 2));
			
	    //List<TenderMain> tenderByDept = tenderMain1Repository.findByDepartmentAndLastDateOfSubmissionGreaterThan(deptId, getTodayEpoch(),PageRequest.of(page,2));

			//List<TenderMain2> tenderMain = tenderMainRepository.findByDepartmentAndLastDateOfSubmissionGreaterThan(deptId, getTodayEpoch());
			
			List<TenderDepartmentEntity> theTenderDepartmentEntity = tenderDepartmentRepository.findAllByOrderByNameAsc();
			
			
			
			//ArrayList<TenderMain> tenderMainHelper=tenderMainVO(tenderByDept);
			

			//theModel.addAttribute("allTenderMain", tenderMainHelper);
			theModel.addAttribute("allTenderMain",tenderMain1Repository.findByDepartmentAndLastDateOfSubmissionGreaterThan(deptId, getTodayEpoch(),PageRequest.of(page,2)));

			theModel.addAttribute("departments",theTenderDepartmentEntity);

			theModel.addAttribute("currentPage", page);
			
			

			System.out.println(deptId);
			 			 
		    return "search";
		}
		//Search by ONLY : KEYWORD@.y
		else if(!general.equals("") && dept.equals("")) {
			
		//	List<TenderMain> tenderMain =tenderMain1Repository.findByDetailContainingAndLastDateOfSubmissionGreaterThan(general, getTodayEpoch());
			
			
			
			//ArrayList<TenderMain2> tenderMainList =tenderMainVO(tenderMain);
			List<TenderDepartmentEntity> theTenderDepartmentEntity = tenderDepartmentRepository.findAllByOrderByNameAsc();
			
			
			theModel.addAttribute("allTenderMain", tenderMain1Repository.findByDetailContainingAndLastDateOfSubmissionGreaterThan(general, getTodayEpoch(),PageRequest.of(page,2)));
			theModel.addAttribute("departments",theTenderDepartmentEntity);
			theModel.addAttribute("currentPage", page);

		    return "search";
			
		}//search by KEYWORD AND DEPT NAME
		else if(!general.equals("") && !dept.equals("")) {
			//TenderDepartmentEntity tenderDept = tenderDepartmentService.findByName(dept);
			//int deptId = tenderDept.getId();
			
			Integer deptId = Integer.parseInt(dept);

			//List<TenderMain> tenderMain =tenderMain1Repository.findByDetailContainingAndDepartmentAndLastDateOfSubmissionGreaterThan(general, deptId, getTodayEpoch());

			//ArrayList<TenderMain2> tenderMainEntityHelperList =tenderMainVO(tenderMain);
			
			
			
			List<TenderDepartmentEntity> theTenderDepartmentEntity = tenderDepartmentRepository.findAllByOrderByNameAsc();
			
			theModel.addAttribute("allTenderMain", tenderMain1Repository.findByDetailContainingAndDepartmentAndLastDateOfSubmissionGreaterThan(general, deptId, getTodayEpoch(),PageRequest.of(page, 3)));
			theModel.addAttribute("departments",theTenderDepartmentEntity);
			theModel.addAttribute("currentPage", page);

		    return "search";
			
		}
		
		else {
	
			//	List<TenderMain> theTenderMain = tenderMain1Repository.findByLastDateOfSubmissionGreaterThan(getTodayEpoch());
				
				//ArrayList<TenderMain2> tenderMainEntityHelperList =tenderMainVO(theTenderMain);			
				
				
				theModel.addAttribute("allTenderMain", tenderMain1Repository.findByLastDateOfSubmissionGreaterThan(getTodayEpoch(),PageRequest.of(page,3)));
				List<TenderDepartmentEntity> theTenderDepartmentEntity = tenderDepartmentRepository.findAllByOrderByNameAsc();
				theModel.addAttribute("departments",theTenderDepartmentEntity);
				
				theModel.addAttribute("currentPage", page);

				System.out.println(" t o d a y   e p  o c h h :"+ getTodayEpoch());

				
				//System.out.println(" :"+ tenderMainEntityHelperList.get(0).);
				return "search";
			}
	    }
	
	
	//RECEIVED THE ARRAY LIST OF TENDER MAIN AND RETURN ARRAYLIST OF TENDERMAIN VIEW OBJECT
//	public ArrayList<TenderMain2> tenderMainVO(List<TenderMain2> myTenderEntity) {
//		ArrayList<TenderMain2> myTenderVO = new  ArrayList<TenderMain2>();
//		
//		for(int i =0;i<myTenderEntity.size();i++) {
//		//1. move easy part to VO
//			TenderMain2 myLittleHelper = new TenderMain2();
//			myLittleHelper.setId(myTenderEntity.get(i).getId());
//			myLittleHelper.setTenderNumber(myTenderEntity.get(i).getTenderNumber());
//			myLittleHelper.setIssueDate(myTenderEntity.get(i).getIssueDate());
//			myLittleHelper.setLastDateOfSubmission(myTenderEntity.get(i).getLastDateOfSubmission());
//			myLittleHelper.setOpeningDate(myTenderEntity.get(i).getOpeningDate());
//			myLittleHelper.setDepartment(myTenderEntity.get(i).getDepartment());
//			myLittleHelper.setSubject(myTenderEntity.get(i).getSubject());
//			myLittleHelper.setDetail(myTenderEntity.get(i).getDetail());
//			myLittleHelper.setAuthor(myTenderEntity.get(i).getAuthor());
//			myLittleHelper.setAttachment(myTenderEntity.get(i).getAttachment());
//			myLittleHelper.setCreated(myTenderEntity.get(i).getCreated());
//			myLittleHelper.setModified(myTenderEntity.get(i).getModified());
//			
//		//	myLittleHelper =myTenderEntity.get(i);
//		//2. convert epoch to readable
//			//GETT THE EPOCH AND SENT TO FN
//			myLittleHelper.setIssueDate1(epochToHumanReadable(myTenderEntity.get(i).getIssueDate()));
//			myLittleHelper.setLastDateOfSubmission1(epochToHumanReadable(myTenderEntity.get(i).getLastDateOfSubmission()));
//			myLittleHelper.setOpeningDate1(epochToHumanReadable(myTenderEntity.get(i).getOpeningDate()));
//			
//			
//			/*
//			 * myLittleHelper.setCreatedVO(epochToHumanReadable(myTenderEntity.get(i).
//			 * getCreated()));
//			 * myLittleHelper.setModifiedVO(epochToHumanReadable(myTenderEntity.get(i).
//			 * getModified()));
//			 */
//
//		//3. convert deptId to deptName
//		Optional<TenderDepartmentEntity> deptname = tenderDepartmentRepository.findById(myTenderEntity.get(i).getDepartment());
//		myLittleHelper.setDepartment1(deptname.get().getName());
//		
//			/*
//			 * //4. split the details String detailVO =
//			 * myTenderEntity.get(i).getDetail();//200char if(detailVO.length()>200) {//take
//			 * the first 200 char detailVO =detailVO.substring(0, 200); }
//			 * myLittleHelper.setDetail1(detailVO);
//			 */
//		
//		//5. add to the array list of Entity VO
//		myTenderVO.add(myLittleHelper);
//		
//		}
//		
//		return myTenderVO;
//	}
	
	public String epochToHumanReadable(long epoch) {

		long mEpoch = epoch * 1000L;
		System.out.println("MY epoch:"+mEpoch);
		
		String date = new java.text.SimpleDateFormat("dd/MM/yyyy").
		format(new java.util.Date (mEpoch));	

		System.out.println("MY date:"+date);

		return date;
	}
	
//	public long getTodayEpoch() throws ParseException {
//	
//		//Integer epochTimeCurrent=0;
//		long epochTimeCurrent=0L;
//
//		
//		Date today = Calendar.getInstance().getTime();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd,MMM yyyy HH:mm:ss "); //"dd,MMM yyyy HH:mm:ss.SSS zzz
//	
//		String currentTime = simpleDateFormat.format(today);
//	
//		System.out.println("current:"+currentTime);
//		Date date = simpleDateFormat.parse(currentTime);
//		System.out.println("date:"+date);
//	
//		//epochTimeCurrent =(int) ( date.getTime()) /1000;// I DIVIDE BY 1000 BECAUSE I WANT TO REMOVE THE MILLI SECONDS. THE MILLI SECONDS IS PRESENT HERE BUT NOT PRESENT IN THE DATA BASE. SO COMPARING IS DIFFICULT
//		epochTimeCurrent = (date.getTime()) /1000L;// I DIVIDE BY 1000 BECAUSE I WANT TO REMOVE THE MILLI SECONDS. THE MILLI SECONDS IS PRESENT HERE BUT NOT PRESENT IN THE DATA BASE. SO COMPARING IS DIFFICULT
//
//		System.out.println("cuepochTimeCurret:"+epochTimeCurrent);
//
//		return epochTimeCurrent;
//	}
	
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
