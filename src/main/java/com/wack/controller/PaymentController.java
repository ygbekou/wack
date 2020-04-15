package com.wack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wack.model.BaseEntity;
import com.wack.model.stock.Payment;
import com.wack.service.PaymentService;


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
		
}
