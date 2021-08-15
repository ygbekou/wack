package com.wack.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.wack.model.Transaction;


@Service(value = "stripeService")
public class StripeProcessingImpl implements PaymentProcessingService {

	private static final Logger LOGGER = Logger.getLogger(StripeProcessingImpl.class);
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${STRIPE_PUBLISHABLE_KEY}")
	private String stripePublishableKey;
	
	@Value("${STRIPE_SECRET_KEY}")
	private String stripeSecretKey;
	
	private static Gson gson = new Gson();

    static class StripeKeyResponse {
        private String publishableKey;

        public StripeKeyResponse(String publishableKey) {
            this.publishableKey = publishableKey;
        }
    }
    

    @Transactional
	public String secret(Transaction transaction) throws StripeException {
    	
		PaymentIntentCreateParams params =
		  PaymentIntentCreateParams.builder()
		    .setCurrency(transaction.getCurrencyCode())
		    .setAmount(Long.valueOf(String.valueOf((new BigDecimal(transaction.getAmount())).setScale(2, RoundingMode.FLOOR)).replace(".", "")))
		    // Verify your integration in this guide by including this parameter
		    .putMetadata("integration_check", "accept_a_payment")
		    .build();

		PaymentIntent intent = PaymentIntent.create(params);
		
		return intent.getClientSecret();
    }

    public String retrievePublishableKey() {
    	return gson.toJson(new StripeKeyResponse(stripePublishableKey));
    }
    

    @PostConstruct
    private void postConstruct() {
        Stripe.apiKey = this.stripeSecretKey;
    }
}
