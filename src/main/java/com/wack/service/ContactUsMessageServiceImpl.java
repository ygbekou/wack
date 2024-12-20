package com.wack.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wack.dao.ProjectDao;
import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.model.ContactUsMessage;
import com.wack.model.ProjectDesc;


@Service(value="contactUsMessageService")
public class ContactUsMessageServiceImpl  implements ContactUsMessageService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;
	
	
	@Transactional
	public BaseEntity save(ContactUsMessage contactUsMessage) {		
		ContactUsMessage saveMessage = (ContactUsMessage) genericService.save(contactUsMessage);
		Company company = this.genericService.getCompany("fr");
		String subject = "Message depuis le site de "+company.getName()+" venant de " + contactUsMessage.getName();
		ArrayList<String> to = new ArrayList<String>();
		for(String a: company.getToEmail().split(",")) {
			to.add(a);
		} 
		
		ProjectDesc projectDesc = projectDao.getProjectDesc(contactUsMessage.getProject().getId(), contactUsMessage.getLang());
		
		if(contactUsMessage.getProject()!=null) {
			subject = "Feedback sur le projet "
					+projectDesc.getTitle()
					+" depuis le site de "+company.getName();
			to.add(contactUsMessage.getProject().getUser().getEmail());
		}else if(contactUsMessage.getNews()!=null) {
			subject = "Feedback sur l'article "
					+contactUsMessage.getNews().getTitle()
					+" depuis le site de "+company.getName();
			to.add(contactUsMessage.getNews().getAuthor().getEmail());
		}
		String emailMessage = "Auteur :    "  + contactUsMessage.getName() + "\n"
							+ "Tel:    "  + (contactUsMessage.getPhone()==null?"": contactUsMessage.getPhone()) + "\n"
							+ "E-mail:    "  + contactUsMessage.getEmail() + "\n\n"
							+ contactUsMessage.getMessage();
		try {
			String[] array = new String[to.size()];
			to.toArray(array); // fill the array
			mailSender.sendMail(contactUsMessage.getEmail(), array, 
				subject, emailMessage);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return saveMessage;
		
	}

}
