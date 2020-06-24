package com.softenza.emarket.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softenza.emarket.domain.GenericChartDto;
import com.softenza.emarket.domain.GenericVO;
import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.stock.Payment;
import com.softenza.emarket.service.PaymentService;
import com.softenza.emarket.util.CacheUtil;

import net.sf.ehcache.Element;


@RestController
@RequestMapping(value="/service/Payment")
@CrossOrigin
public class PaymentController extends BaseController {

		@Autowired
		@Qualifier("paymentService")
		PaymentService paymentService;
	
	
		@RequestMapping(value="/save",method = RequestMethod.POST)
		public BaseEntity save(@RequestBody Payment payment) throws Exception {
				
			this.paymentService.save(payment);
			
			return payment;
		}
		
		@RequestMapping(value="statistic/months",method = RequestMethod.GET)
		public GenericChartDto getAllActiveReferences() throws ClassNotFoundException{
			GenericChartDto result =  paymentService.getMonthlyPayments();
			
			return result;
		}
		
}
