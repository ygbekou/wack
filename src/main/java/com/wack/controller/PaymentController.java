package com.wack.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wack.domain.GenericChartDto;
import com.wack.domain.PaymentResponse;
import com.wack.model.BaseEntity;
import com.wack.model.Transaction;
import com.wack.model.stock.Payment;
import com.wack.service.PaymentProcessingService;
import com.wack.service.PaymentService;


@RestController
@RequestMapping(value="/service/Payment")
@CrossOrigin
public class PaymentController extends BaseController {

		@Autowired
		@Qualifier("paymentService")
		PaymentService paymentService;
		
		@Autowired
		@Qualifier("stripeService")
		PaymentProcessingService stripeService;
	
	
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
		
		@RequestMapping(value = "/stripe-key", method = RequestMethod.GET)
		public String retrieveStripeKey() {
			return this.stripeService.retrievePublishableKey();
		}
		
		@RequestMapping(value="/clientSecret", method = RequestMethod.POST)
		public PaymentResponse secret(@RequestBody Transaction transaction) {
			PaymentResponse response;
			try {
				response = new PaymentResponse(stripeService.secret(transaction));
			} catch(Exception ex) {
				response = new PaymentResponse();
				response.setErrorCode("FAILURE");
			}
			
			return response;
		}
		
		@RequestMapping(value="/saveTransaction",method = RequestMethod.POST)
		public BaseEntity save(@RequestBody Transaction transaction) throws Exception {
				
			this.paymentService.save(transaction);
			
			return transaction;
		}
		
}
