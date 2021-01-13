/*
 * 
 * 
 * Thanpuia Fanai
 * 
 * 
 * 
 * */


package com.etender.SecurityRole.Entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class TenderMain {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private Integer id;
	
	@Column (name="tender_number")
	private String tenderNumber;
	
	@Column (name="issue_date")
	private Integer issueDate;
	
	@Column (name="last_date_of_submission")
	private Integer lastDateOfSubmission;
	
	@Column (name="opening_date")
	private Integer openingDate;
	
	@Column (name="department")
	private Integer department;
	
	@Column (name="subject")
	private String subject;
	
	@Column (name="detail")
	private String detail;
	
	@Column (name="author")
	private Integer author;
	
	@Column (name="attachment")
	private String attachment;
	
	@Column (name="created")
	private Integer created;
	
	@Column (name="modified")
	private Integer modified;
	


@Transient private String title;

@Transient private String seoTitle;
@Transient private String description;

@Transient private String url;
 
 @Transient private String downloadCount;

@Transient private Integer mpublic;


@Transient
private String issueDate1;

@Transient
private String lastDateOfSubmission1;

@Transient
private String openingDate1;

@Transient
private String department1;


//@Transient
//private String issueDateVO;
//@Transient
//private String lastDateOfSubmissionVO;
//@Transient
//private String openingDateVO;

//@Transient
//private String detailVO;
//@Transient
//private String authorVO;
//@Transient
//private String createdVO;
//@Transient
//private String modifiedVO; 





	
	
	
	public TenderMain() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTenderNumber() {
		return tenderNumber;
	}


	public void setTenderNumber(String tenderNumber) {
		this.tenderNumber = tenderNumber;
	}


	public Integer getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(Integer issueDate) {
		this.issueDate = issueDate;
	}


	public Integer getLastDateOfSubmission() {
		return lastDateOfSubmission;
	}


	public void setLastDateOfSubmission(Integer lastDateOfSubmission) {
		this.lastDateOfSubmission = lastDateOfSubmission;
	}


	public Integer getOpeningDate() {
		return openingDate;
	}


	public void setOpeningDate(Integer openingDate) {
		this.openingDate = openingDate;
	}


	public Integer getDepartment() {
		return department;
	}


	public void setDepartment(Integer department) {
		this.department = department;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public Integer getAuthor() {
		return author;
	}


	public void setAuthor(Integer author) {
		this.author = author;
	}


	public String getAttachment() {
		return attachment;
	}


	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}


	public Integer getCreated() {
		return created;
	}


	public void setCreated(Integer created) {
		this.created = created;
	}


	public Integer getModified() {
		return modified;
	}


	public void setModified(Integer modified) {
		this.modified = modified;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSeoTitle() {
		return seoTitle;
	}


	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getDownloadCount() {
		return downloadCount;
	}


	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}


	public Integer getMpublic() {
		return mpublic;
	}


	public void setMpublic(Integer mpublic) {
		this.mpublic = mpublic;
	}


	public String getIssueDate1() {
		return issueDate1;
	}


	public void setIssueDate1(String issueDate1) {
		this.issueDate1 = issueDate1;
	}


	public String getLastDateOfSubmission1() {
		return lastDateOfSubmission1;
	}


	public void setLastDateOfSubmission1(String lastDateOfSubmission1) {
		this.lastDateOfSubmission1 = lastDateOfSubmission1;
	}


	public String getOpeningDate1() {
		return openingDate1;
	}


	public void setOpeningDate1(String openingDate1) {
		this.openingDate1 = openingDate1;
	}


	public String getDepartment1() {
		return department1;
	}


	public void setDepartment1(String department1) {
		this.department1 = department1;
	}


	public TenderMain(Integer id, String tenderNumber, Integer issueDate, Integer lastDateOfSubmission,
			Integer openingDate, Integer department, String subject, String detail, Integer author, String attachment,
			Integer created, Integer modified) {
	
		this.id = id;
		this.tenderNumber = tenderNumber;
		this.issueDate = issueDate;
		this.lastDateOfSubmission = lastDateOfSubmission;
		this.openingDate = openingDate;
		this.department = department;
		this.subject = subject;
		this.detail = detail;
		this.author = author;
		this.attachment = attachment;
		this.created = created;
		this.modified = modified;
	}


	public TenderMain(String tenderNumber, Integer issueDate, Integer lastDateOfSubmission, Integer openingDate,
			Integer department, String subject, String detail, Integer author, String attachment, Integer created,
			Integer modified) {
	
		this.tenderNumber = tenderNumber;
		this.issueDate = issueDate;
		this.lastDateOfSubmission = lastDateOfSubmission;
		this.openingDate = openingDate;
		this.department = department;
		this.subject = subject;
		this.detail = detail;
		this.author = author;
		this.attachment = attachment;
		this.created = created;
		this.modified = modified;
	}



}
