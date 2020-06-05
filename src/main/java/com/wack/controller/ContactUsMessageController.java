package com.wack.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.wack.model.BaseEntity;
import com.wack.model.ContactUsMessage;
import com.wack.service.ContactUsMessageService;

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
