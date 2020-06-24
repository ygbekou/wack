package com.softenza.emarket.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.ContactUsMessage;
import com.softenza.emarket.service.ContactUsMessageService;

@RestController
@RequestMapping(value = "/service/ContactUsMessage")
@CrossOrigin
public class ContactUsMessageController extends BaseController {
	@Autowired
	@Qualifier("contactUsMessageService")
	ContactUsMessageService contactUsMessageService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BaseEntity save(@RequestBody BaseEntity be) {
		this.contactUsMessageService.save((ContactUsMessage) be);
		return be;
	}

}
