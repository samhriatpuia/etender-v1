/*
 * Thanpuia Fanai
*/
package com.etender.SecurityRole.Controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.etender.SecurityRole.Entity.DownloadEntity;
import com.etender.SecurityRole.Entity.TenderDepartmentEntity;
import com.etender.SecurityRole.Entity.TenderMain;
import com.etender.SecurityRole.Entity.User;
import com.etender.SecurityRole.Repository.DownloadRepository;
import com.etender.SecurityRole.Repository.TenderDepartmentRepository;
import com.etender.SecurityRole.Repository.TenderMain1Repository;
import com.etender.SecurityRole.Repository.TenderSupplierUserRepository;
import com.etender.SecurityRole.Repository.UserRepository;
import com.etender.SecurityRole.Services.MyHelper;


@Controller
@RequestMapping("/")
public class UploadController {
	
	
	long epoch = System.currentTimeMillis()/1000;
	int j = (int) epoch;
	
	Integer k;
	
	ArrayList<Integer> UploadFile = new ArrayList<Integer>();   //Temporary File Upload ID save na
	
	
	
	@Autowired
	DownloadRepository downloadRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	//private String[] list =new String[30];
	ArrayList<String> list = new ArrayList<>();
	
	//private String[] myArray= new String[10];
	
	@Autowired
	TenderMain1Repository tenderMain1Repository;
	
	@Autowired	
	TenderDepartmentRepository tenderDepartmentRepository;
	
	
	@Autowired
	TenderSupplierUserRepository tenderSupplierUserRepository;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/Users/lalthanpuiafanai/etender_document/";

    @GetMapping("/upload")
    public String indexupload(Model model) {
    	
    	
    	 model.addAttribute("FileArray",this.UploadFile);
         model.addAttribute("downloadFile",downloadRepository.findAll());
    	
    	model.addAttribute("tenderMain", new TenderMain());
    	
    	model.addAttribute("departments",tenderDepartmentRepository.findAll());
    	
        return "views/upload";
    }
    
    
    
    @PostMapping("/uploadimg") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("fileupload") MultipartFile file,
                                   RedirectAttributes redirectAttributes,Model theModel) {
    	
    	
    	
    	
    	
    	

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/upload";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            File file1=new File(UPLOADED_FOLDER+"/tender");
            if (!file1.exists()) {
				file1.mkdirs();
			}
            Path path = Paths.get(UPLOADED_FOLDER+"/tender/" + file.getOriginalFilename());
           System.out.println(UPLOADED_FOLDER+"/tender/"+file.getOriginalFilename());
            Files.write(path, bytes);

       redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            
 
            
            redirectAttributes.addFlashAttribute("Imgpath","/viewimage?Imgpath="+UPLOADED_FOLDER+"/tender/"+file.getOriginalFilename());

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        DownloadEntity downloadEntity=new DownloadEntity();
        
        
        downloadEntity.setTitle(file.getOriginalFilename());
        
        downloadEntity.setAuthor("2");
        
        downloadEntity.setUrl(UPLOADED_FOLDER);
        
        downloadEntity.setDownload_count(0);
        
        downloadEntity.setCreated(j);
        
        downloadEntity.setMpublic(1);
        
        
        downloadRepository.save(downloadEntity);
        
        k = downloadEntity.getId();
        
        
        this.UploadFile.add(k);     // Store ID of File Upload temporarily
        
        
        System.out.println("this.UploadFile:"+this.UploadFile);
        
        
        
        System.out.println(k);
        
        
       String str1 = Integer.toString(k); 
       
      
       this.list.add(str1);
       
   
       System.out.println("this.List: "+this.list);
       
     
    	   


      return "redirect:/upload";
    }

    
    @PostMapping("/upload")
    public String supplierUser(@Valid TenderMain tenderMain, BindingResult bindingResult, Model model) throws ParseException {
        

    	UserDetails userDetails =
    			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	User details= userRepository.findByEmail(userDetails.getUsername());
    	
    	tenderMain.setAuthor(details.getUserId());
      
    	
    	model.addAttribute("tenderMain", new TenderMain());
    	
    	model.addAttribute("departments",tenderDepartmentRepository.findAll());
		
		
		model.addAttribute("departments",tenderDepartmentRepository.findAll());
		
	System.out.println(tenderMain.getIssueDate());

	if(bindingResult.hasErrors()) {
			return "views/upload";
	}
	
		
//		if(userService.isUserPresent(user.getEmail())) {
//			model.addAttribute("exist",true);
//
//			return "views/RegisterSupplier";
//
//		}
		
		//String str= this.list.toString();


	 // Convert the List of String to String 
    String string = String.join(", ", this.list); 
    
    System.out.println(string);
    
		
//		
//		String str1 = tenderMain1.getIssue_date1();
//		SimpleDateFormat df = new SimpleDateFormat("YOUR String Date PATTERN");
//		Date date = df.parse(str);
//		long epoch = date.getTime();
//		System.out.println(epoch);
		
    

    

    
    System.out.println(epoch);
    
    tenderMain.setAttachment(string);
	tenderMain.setCreated(j);
	tenderMain.setModified(j);
	System.out.println("issue date");
	System.out.println(tenderMain.getIssueDate1());
	tenderMain.setIssueDate(SEConvert(tenderMain.getIssueDate1()));
	
	
	System.out.println("$$$$$$$$$$$$$$last date of submission");
	System.out.println(tenderMain.getLastDateOfSubmission1());
	tenderMain.setLastDateOfSubmission(SEConvert(tenderMain.getLastDateOfSubmission1()));
	
	System.out.println("$$$$$$$$$$$$$$oppening date");
	System.out.println(tenderMain.getOpeningDate1());
	tenderMain.setOpeningDate(SEConvert(tenderMain.getOpeningDate1()));
	
	
	System.out.println(tenderMain.getAttachment());
		
	tenderMain1Repository.save(tenderMain);
	
	
	Optional<TenderDepartmentEntity> depUser=tenderDepartmentRepository.findById(tenderMain.getDepartment());
	
	String name=depUser.get().getName();
			
	MyHelper.alertSubscriberSms(tenderMain.getDepartment(),tenderMain,name,tenderSupplierUserRepository,userRepository);
	MyHelper.alertSubscriberEmail(tenderMain.getDepartment(),tenderMain,name,tenderSupplierUserRepository,userRepository,javaMailSender);
	
	
	
	
	this.list.clear();
	
	this.UploadFile.clear();
	

	

		
				return "redirect:/index";

	}
    
  // Funtion for Converting String Date to Integer Epoch 
    
    public Integer SEConvert(String str) throws ParseException
    {	
    	
    	
    
    	
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(str);
        long ep = date.getTime()/1000L;
        int z = (int) ep;
        
        
        System.out.println("MSeGS");
        System.out.println(ep);
        
        
        return z;

        
     
       

     
        
    }
    

    
    @GetMapping("/deleteDownload")
	public String deleteDownload(Model theModel,@RequestParam int page) {
    	
    	
    	
    	
    	
    	
    	downloadRepository.deleteById(page);
    	
    	System.out.print("Remove Download ID:"+page);
    	
    	for(int i=0;i<this.UploadFile.size();i++)
    	{
    		
    		
    		
    		if(page==this.UploadFile.get(i)) {
    			
    			
    			
    			System.out.print("Remove.this.UploadFile"+this.UploadFile.get(i)+"hurray hurray");
    			
    			this.UploadFile.remove(i);
    			
    			
    			
    			System.out.println("ArrayList Delete after: "+this.UploadFile);
    			
    			
    		}
    		
    		
    		
    		
    		
    	}
    	
    
    	
	
    	
    	return "redirect:/upload";
    	
	

    	
    }
    
    
    
    
    

   
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "views/uploadStatus";
    }
    
    @GetMapping("/viewimage")
    public void viewImage(HttpServletRequest request, HttpServletResponse response,@RequestParam("Imgpath") String Imgpath) throws IOException {
    	
    	response.setContentType("text/plain");
    	response.setHeader("Content-disposition", "attachment; filename=sample.jpg");
    	final File initialFile = new File(Imgpath);
        final InputStream targetStream = 
          new DataInputStream(new FileInputStream(initialFile));
        
          OutputStream out = response.getOutputStream();
 
            byte[] buffer = new byte[2048];
         
            int numBytesRead;
            while ((numBytesRead = targetStream.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            
        }
		
	}
    

}