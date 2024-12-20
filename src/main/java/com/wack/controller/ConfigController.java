package com.wack.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wack.domain.GenericSearchCriteria;
import com.wack.model.stock.Product;
import com.wack.service.ConfigService;


@RestController
@RequestMapping(value="/service/config")
@CrossOrigin
public class ConfigController extends BaseController {

		@Autowired
		@Qualifier("configService")
		ConfigService configService;
	
	
		@RequestMapping(value="/product/search",method = RequestMethod.POST)
		public List<Product> save(@RequestBody GenericSearchCriteria searchCriteria) throws Exception {
				
			List<Product> products = configService.getProducts(searchCriteria);
			
			return products;
		}

}
