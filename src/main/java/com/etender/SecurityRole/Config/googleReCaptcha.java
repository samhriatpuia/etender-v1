package com.etender.SecurityRole.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public class googleReCaptcha {
	
	@Component
	@ConfigurationProperties(prefix = "google.recaptcha.key")
	public class CaptchaSettings {
	 
	    private String site;
	    private String secret;
	 
	    
	}

	}



