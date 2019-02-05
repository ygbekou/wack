package com.wack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wack.model.BaseEntity;
import com.wack.model.ContactUsMessage;
import com.wack.service.ContactUsMessageService;


@RestController
@RequestMapping(value="/service/ContactUsMessage")
@CrossOrigin
public class ContactUsMessageController extends BaseController {

		@Autowired
		@Qualifier("contactUsMessageService")
		ContactUsMessageService contactUsMessageService;
	
	
		@RequestMapping(value="/save",method = RequestMethod.POST)
		public BaseEntity save(@RequestBody ContactUsMessage contactUsMessage) {
				
			this.contactUsMessageService.save(contactUsMessage);
			
			return contactUsMessage;
		}
		
}
