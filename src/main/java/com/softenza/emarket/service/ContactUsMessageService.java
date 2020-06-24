package com.softenza.emarket.service;

import org.springframework.stereotype.Service;

import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.ContactUsMessage;

@Service(value="contactUsMessageService")
public interface ContactUsMessageService {
	
	public BaseEntity save(ContactUsMessage contactUsMessage);
}
