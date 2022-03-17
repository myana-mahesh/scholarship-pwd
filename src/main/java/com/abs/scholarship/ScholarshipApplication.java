package com.abs.scholarship;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abs.scholarship.service.MailService;


@SpringBootApplication
public class ScholarshipApplication {
	
	@Autowired
    private final static MailService mailService=new MailService();

	public static void main(String[] args) {
		SpringApplication.run(ScholarshipApplication.class, args);
	}

}
