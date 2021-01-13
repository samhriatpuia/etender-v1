package com.etender.SecurityRole.Controller;

//Created by Lalthanpuia Chhangte

import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.etender.SecurityRole.Entity.TenderDepartmentEntity;
import com.etender.SecurityRole.Entity.TenderMain;

import com.etender.SecurityRole.Repository.TenderDepartmentRepository;
import com.etender.SecurityRole.Repository.TenderMain1Repository;

@Controller
@RequestMapping("/archive")
public class ArchiveController {

	@Autowired
	private TenderMain1Repository tenderMain1Repository;

	@Autowired
	private TenderDepartmentRepository tenderDepartmentRepository;

	ArchiveController(TenderMain1Repository tenderMain1Repository,
			TenderDepartmentRepository tenderDepartmentRepository) {

		this.tenderDepartmentRepository = tenderDepartmentRepository;
		this.tenderMain1Repository = tenderMain1Repository;

	}

	@GetMapping("/myArchive/{mMonth}/{mYear}")
	public String archived(Model theModel, @PathVariable String mMonth, @PathVariable String mYear,
			@RequestParam(defaultValue = "0") int page) throws ParseException {

		theModel.addAttribute("showAllValid", "Show all valid");

		String str = mMonth + " 01 " + mYear;
		System.out.println("date:" + str);

		SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");
		Date date = df.parse(str);

//		long epochofSelectedArchived = (long) date.getTime() / 1000L; //remove the milliseconds
//		long epochofNextMonth = getTheNextMonthInEpoch(mMonth, mYear) / 1000L; //remove the milliseconds		
		Integer epochofSelectedArchived = (int) (date.getTime() / 1000L); // remove the milliseconds
		Integer epochofNextMonth = (int) (getTheNextMonthInEpoch(mMonth, mYear) / 1000L); // remove the milliseconds

		System.out.println("start:" + epochofSelectedArchived + "\n end:" + epochofNextMonth);
		// List<TenderMain> tenderMainSelectedArchive =
		// tenderMain1Repository.findByIssueDateBetween(epochofSelectedArchived,epochofNextMonth);
		List<TenderDepartmentEntity> theTenderDepartmentEntity = tenderDepartmentRepository.findAllByOrderByNameAsc();

		// ArrayList<TenderMain2> tenderMainEntityHelperList
		// =tenderMainVO(tenderMainSelectedArchive);

		theModel.addAttribute("departments", theTenderDepartmentEntity);

		// theModel.addAttribute("allTenderMain", tenderMainEntityHelperList);
		theModel.addAttribute("allTenderMain", tenderMain1Repository.findByIssueDateBetween(epochofSelectedArchived,
				epochofNextMonth, PageRequest.of(page, 2)));

		theModel.addAttribute("currentPage", page);

		theModel.addAttribute("my", mMonth + "/" + mYear);

		// theModel.addAttribute("mm",mMonth);

		// return "views/tenderViewFinal";
		return "archrive";
	}

	/*
	 * //RECEIVED THE ARRAY LIST OF TENDER MAIN AND RETURN ARRAYLIST OF TENDERMAIN
	 * VIEW OBJECT public ArrayList<TenderMain2> tenderMainVO(List<TenderMain2>
	 * myTenderEntity) { ArrayList<TenderMain2> myTenderVO = new
	 * ArrayList<TenderMain2>();
	 * 
	 * for(int i =0;i<myTenderEntity.size();i++) { //1. move easy part to VO
	 * TenderMain2 myLittleHelper = new TenderMain2();
	 * myLittleHelper.setId(myTenderEntity.get(i).getId());
	 * myLittleHelper.setTenderNumber(myTenderEntity.get(i).getTenderNumber());
	 * myLittleHelper.setIssueDate(myTenderEntity.get(i).getIssueDate());
	 * myLittleHelper.setLastDateOfSubmission(myTenderEntity.get(i).
	 * getLastDateOfSubmission());
	 * myLittleHelper.setOpeningDate(myTenderEntity.get(i).getOpeningDate());
	 * myLittleHelper.setDepartment(myTenderEntity.get(i).getDepartment());
	 * myLittleHelper.setSubject(myTenderEntity.get(i).getSubject());
	 * myLittleHelper.setDetail(myTenderEntity.get(i).getDetail());
	 * myLittleHelper.setAuthor(myTenderEntity.get(i).getAuthor());
	 * myLittleHelper.setAttachment(myTenderEntity.get(i).getAttachment());
	 * myLittleHelper.setCreated(myTenderEntity.get(i).getCreated());
	 * myLittleHelper.setModified(myTenderEntity.get(i).getModified());
	 * 
	 * // myLittleHelper =myTenderEntity.get(i); //2. convert epoch to readable
	 * //GETT THE EPOCH AND SENT TO FN
	 * myLittleHelper.setIssueDate1(epochToHumanReadable(myTenderEntity.get(i).
	 * getIssueDate()));
	 * myLittleHelper.setLastDateOfSubmission1(epochToHumanReadable(myTenderEntity.
	 * get(i).getLastDateOfSubmission()));
	 * myLittleHelper.setOpeningDate1(epochToHumanReadable(myTenderEntity.get(i).
	 * getOpeningDate()));
	 * 
	 * 
	 * 
	 * myLittleHelper.setCreatedVO(epochToHumanReadable(myTenderEntity.get(i).
	 * getCreated()));
	 * myLittleHelper.setModifiedVO(epochToHumanReadable(myTenderEntity.get(i).
	 * getModified()));
	 * 
	 * 
	 * //3. convert deptId to deptName Optional<TenderDepartmentEntity> deptname =
	 * tenderDepartmentRepository.findById(myTenderEntity.get(i).getDepartment());
	 * myLittleHelper.setDepartment1(deptname.get().getName());
	 * 
	 * 
	 * //4. split the details String detailVO =
	 * myTenderEntity.get(i).getDetail();//200char if(detailVO.length()>200) {//take
	 * the first 200 char detailVO =detailVO.substring(0, 200); }
	 * myLittleHelper.setDetail1(detailVO);
	 * 
	 * 
	 * //5. add to the array list of Entity VO myTenderVO.add(myLittleHelper);
	 * 
	 * }
	 * 
	 * return myTenderVO; }
	 */

	public String epochToHumanReadable(long epoch) {

		long mEpoch = epoch * 1000L;
		System.out.println("MY epoch:" + mEpoch);

		String date = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(mEpoch));

		System.out.println("MY date:" + date);

		return date;
	}

	public long getTodayEpoch() throws ParseException {

		long epochTimeCurrent = 0;
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd,MMM yyyy HH:mm:ss "); // "dd,MMM yyyy HH:mm:ss.SSS
																							// zzz

		String currentTime = simpleDateFormat.format(today);

		Date date = simpleDateFormat.parse(currentTime);

		epochTimeCurrent = (long) (date.getTime()) / 1000L;// I DIVIDE BY 1000 BECAUSE I WANT TO REMOVE THE MILLI
															// SECONDS. THE MILLI SECONDS IS PRESENT HERE BUT NOT
															// PRESENT IN THE DATA BASE. SO COMPARING IS DIFFICULT

		return epochTimeCurrent;
	}

	public long getTheNextMonthInEpoch(String month, String year) throws ParseException {

		String string = "01/" + month + "/" + year + " 01:00:00"; // assuming input
		DateFormat dateformat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
		Date today = dateformat.parse(string);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);

		Date lastDayOfMonth = calendar.getTime();

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Today            : " + sdf.format(today));
		System.out.println("Last Day of Month: " + sdf.format(lastDayOfMonth));

		long epoch = (long) new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
				.parse(sdf.format(lastDayOfMonth) + " 01:00:00").getTime();/// 1000;
		return epoch;
	}

}
