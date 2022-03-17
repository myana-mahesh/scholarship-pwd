package com.abs.scholarship.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abs.scholarship.dto.MailRequest;
import com.abs.scholarship.service.MailService;

@RestController
@RequestMapping("/api/email")
@CrossOrigin()
public class EmailController {
	
	@Autowired
    private final static MailService mailService=new MailService();
	
	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody MailRequest mailRequest){
		try {
			mailService.sendmail(mailRequest);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> test(){
		
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	

}
