package com.wack.service;

import org.springframework.stereotype.Service;

import com.wack.model.BaseEntity;
import com.wack.model.ContactUsMessage;

@Service(value="contactUsMessageService")
public interface ContactUsMessageService {
	
	public BaseEntity save(ContactUsMessage contactUsMessage);
}
