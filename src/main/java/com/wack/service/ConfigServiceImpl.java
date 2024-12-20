package com.wack.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.wack.dao.ConfigDao;
import com.wack.domain.GenericSearchCriteria;
import com.wack.model.stock.Product;

@Service(value="configService")
public class ConfigServiceImpl  implements ConfigService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	ConfigDao configDao;
	
	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;
	
	
	public List<Product> getProducts(GenericSearchCriteria searchCriteria) {
		return configDao.getProducts(searchCriteria);
	}
}
