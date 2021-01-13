package com.etender.SecurityRole.Controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etender.SecurityRole.Entity.DownloadEntity;
import com.etender.SecurityRole.Entity.TenderMain;
import com.etender.SecurityRole.Repository.DownloadRepository;
import com.etender.SecurityRole.Repository.TenderMain1Repository;


@Controller
@RequestMapping("/download")
public class DownloadController {
	//private DownloadService downloadService;

	private TenderMain1Repository tenderMain1Repository;
	private DownloadRepository downloadRepository;
	
	@Autowired
	private ServletContext servletContext;
	
	public DownloadController(TenderMain1Repository mtenderMain1Repository, DownloadRepository mdownloadRepository) {
		this.tenderMain1Repository = mtenderMain1Repository;
		this.downloadRepository = mdownloadRepository;
		
	}
	
//	@GetMapping("/test")
//	public String getAllTender() {
//		
//		//List<TenderMainEntity> theTenderMain = tenderMainService.findAll();
//		//theModel.addAttribute("allTenderMain", theTenderMain);
//		DownloadEntity download = downloadService.findById(90);
//		System.out.println("The selected download is: "+download.getUrl());
//
//		return "tenderView";
//	}
//	
	//THIS IS DAH CHAWP
	@GetMapping("/downloadFile/{tenderId}")
	public ResponseEntity<InputStreamResource> download(HttpServletRequest request,
            HttpServletResponse response, @PathVariable int tenderId) throws IOException {
		
		ArrayList<String> urls  = new ArrayList<String>();
		//DOWNLOAD ALL THE ATTACHED FILE
		Optional<TenderMain> tender = tenderMain1Repository.findById(tenderId);
		String attachment = tender.get().getAttachment();
		
		System.out.println("The attachments is: "+attachment);

		//SEPARATE THE ATTACHMENT FILE INTO LIST IF THERE ARE MORE THAN ONE
		List<String> attachments = Arrays.asList(attachment.split("\\s*,\\s*"));

		
		System.out.println("The size: "+attachments.size());

		//DOWNLOAD THE URL ACCORDING TO THE ATTACHMENTS LIST |  WE ARE USING LOOP SINCE THERE MIGHT BE MORE THAN ONE ATTACHMENT
		try {
			for(int i = 0 ;i<attachments.size();i++) {
				int j = Integer.parseInt(attachments.get(i));
				DownloadEntity download = downloadRepository.findById(j);
				System.out.println("The selected url is: "+download.getUrl());
				urls.add(download.getUrl());
			}
		}catch(Exception e) {}
		
		
		//test
		//convert to zip if attachment +1
		if(urls.size()>=2) {
			zipIt(urls, response);
		}
		
		
		//DOWNLOAD THE FILe
		
		ResponseEntity<InputStreamResource> re;
		//int k =0;
		//while( k <4) {
		System.out.println("Downloading starts...");
		///home/lalthanpuia/Downloads/e_tender/DESIGN

        String tempURL = "/Users/lalthanpuiafanai/Documents/";
        String tempFileName ="EXPENDITURE.docx";
        
        
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(servletContext, tempFileName);

        File file = new File(tempURL + "/" + tempFileName);
        InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//k++;
		return    ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
		
     //   k++;
        //Authorized user will download the file
//        String dataDirectory = request.getServletContext().getRealPath(tempURL);
//        Path file = Paths.get(dataDirectory, tempFileName);
//        System.out.println("itsR");
//
//        if (Files.exists(file))
//        {
//            response.setContentType("application/pdf");
//            response.addHeader("Content-Disposition", "attachment; filename="+tempFileName);
//            try
//            {
//                Files.copy(file, response.getOutputStream());
//                response.getOutputStream().flush();
//                System.out.println("RR");
//            }
//            catch (IOException ex) {
//                ex.printStackTrace();
//                
//                System.out.println("catch");
//
//            }
//            
//        }else {
//            System.out.println("elseee");
//
//        }
	//}
        //end
		//return null;
        
		//return "download";
	}
	
	public void zipIt(ArrayList<String> urls, HttpServletResponse response) throws IOException {

        String tempURL = "/Users/lalthanpuiafanai/Documents/";
        String tempFileName1 ="EXPENDITURE.docx";
        String tempFileName2 ="EXPENDITURE1.docx";

        //Create list for file URLs - these are files from all different locations
        
        //List<String> filenames = urls;
        List<String> filenames = Arrays.asList(tempURL+tempFileName1, tempURL+tempFileName2);
        
        //..code to add URLs to the list
        byte[] buf = new byte[2048];
        // Create the ZIP file
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(baos);
        // Compress the files
        for (int i=0; i<filenames.size(); i++) {
        FileInputStream fis = new FileInputStream(filenames.get(i).toString());
        BufferedInputStream bis = new BufferedInputStream(fis);
        // Add ZIP entry to output stream.
        File file = new File(filenames.get(i).toString());
        String entryname = file.getName();
        out.putNextEntry(new ZipEntry(entryname));
        int bytesRead;
        while ((bytesRead = bis.read(buf)) != -1) {
        out.write(buf, 0, bytesRead);
        }
        out.closeEntry();
        bis.close();
        fis.close();
        }
        out.flush();
        baos.flush();
        out.close();
        baos.close();
        ServletOutputStream sos = response.getOutputStream();
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"MyZip.ZIP\"");
        sos.write(baos.toByteArray());
        out.flush();
        out.close();
        sos.flush();
	}
}

