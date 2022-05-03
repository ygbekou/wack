package com.wack.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stripe.exception.StripeException;
import com.wack.domain.PaygateglobalConfirmationEntity;
import com.wack.domain.PaygateglobalRequestEntity;
import com.wack.domain.PaygateglobalResponseEntity;
import com.wack.domain.PaygateglobalVerificationEntity;
import com.wack.model.Transaction;
import com.wack.model.User;
import com.wack.util.Utils;

@Service(value = "paygateGlobalService")
public class PaygateGlobalProcessingImpl implements PaymentProcessingService {

	@Autowired
	GenericService genericService;

	@Autowired
	RestTemplate restTemplate;

	@Value("${paymentMethod.paygateglobal.requesturl}")
	private String paygateglobalRequestUrl = "https://paygateglobal.com/api/v1/pay";

	@Value("${paymentMethod.paygateglobal.pageurl}")
	private String paygateglobalPageUrl = "https://paygateglobal.com/v1/page";

	@Value("${paymentMethod.paygateglobal.verificationurl}")
	private String paygateglobalVerificationUrl = "https://paygateglobal.com/api/v2/status";

	@Value("${paymentMethod.paygateglobal.api}")
	private String paygateglobalApi = "7aebc45c-f20e-49d8-8156-7e8f923ea3f8";

	@Value("${order.wait.time}")
	private static int ORDER_WAIT_TIME = 120000;

	@Value("${order.wait.interval}")
	private static int ORDER_WAIT_INTERVAL = 1000;

	@Transactional
	public void processPayment(Transaction transaction) throws InterruptedException {

		String requestEntityId = "P - " + transaction.getId().toString();
		Long montant = new Long(String
				.valueOf((new BigDecimal(transaction.getAmount()))
						.setScale(Utils.getCurrencyDecimalPlaces(transaction.getCurrencyCode()), RoundingMode.FLOOR))
				.replace(".", ""));

		System.out.println(" Amount: " + String.valueOf(montant) + ", " + " Transaction ID " + transaction.getId()
				+ " for project " + transaction.getProject().getTitle() + " by User with ID "
				+ transaction.getUser().getId());

		PaygateglobalRequestEntity requestEntity = new PaygateglobalRequestEntity(paygateglobalApi,
				transaction.getPhone(), String.valueOf(montant),
				" Transaction ID " + transaction.getId() + " for project " + transaction.getProject().getTitle()
						+ " by User with ID " + transaction.getUser().getId(),
						requestEntityId);

		ResponseEntity<PaygateglobalResponseEntity> responseEntity = restTemplate.postForEntity(paygateglobalRequestUrl,
				requestEntity, PaygateglobalResponseEntity.class);

		Long startTime = System.currentTimeMillis();

		if ("0".equals(responseEntity.getBody().getStatus())) {

			PaygateglobalVerificationEntity verificationEntity = new PaygateglobalVerificationEntity(paygateglobalApi,
					requestEntityId);

			responseEntity = restTemplate.postForEntity(paygateglobalVerificationUrl, verificationEntity,
					PaygateglobalResponseEntity.class);

			while ("2".equals(responseEntity.getBody().getStatus())
					&& System.currentTimeMillis() - startTime < ORDER_WAIT_TIME) {
				Thread.sleep(ORDER_WAIT_INTERVAL);

				responseEntity = restTemplate.postForEntity(paygateglobalVerificationUrl, verificationEntity,
						PaygateglobalResponseEntity.class);

			}

			if (!"0".equals(responseEntity.getBody().getStatus())) {
				System.out.println("FLOOZ STATUS : " + responseEntity.getBody().getStatus());
				transaction.setErrors(Arrays.asList("PAYGATE" + responseEntity.getBody().getStatus()));
			}
		} else {
			System.out.println("FLOOZ STATUS : " + responseEntity.getBody().getStatus());
			transaction.setErrors(Arrays.asList("PAYGATE" + responseEntity.getBody().getStatus()));
		}

	}

	public Transaction processPaymentConfirmation(PaygateglobalConfirmationEntity confirmationEntity) {
		
		Transaction transaction = (Transaction) genericService.find(Transaction.class, Long.valueOf(confirmationEntity.getIdentifier()));

		if (!(transaction.getStatus() == 1)) {
			transaction.setErrors(Arrays.asList("TRANSACTION_ALREADY_PROCESSED"));
			return transaction;
		}
		try {

			PaygateglobalVerificationEntity verificationEntity = new PaygateglobalVerificationEntity(paygateglobalApi,
					transaction.getId().toString());

			ResponseEntity<PaygateglobalResponseEntity> responseEntity = restTemplate
					.postForEntity(paygateglobalVerificationUrl, verificationEntity, PaygateglobalResponseEntity.class);

			if ("0".equals(responseEntity.getBody().getStatus())) {
				transaction.setStatus(2);
			} else {
				transaction.setFailureReason("TMONEY STATUS : " + responseEntity.getBody().getStatus());
				transaction.setStatus(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			transaction.setFailureReason(ex.getMessage());
			transaction.setStatus(1);
		}

		return transaction;
	}

	public void setRedirectionPaymentUrl(Transaction transaction) {
		StringBuilder builder = new StringBuilder();
		User user = (User) genericService.find(User.class, transaction.getUser().getId());
		/*
		 * builder.append(this.paygateglobalPageUrl).append("?token=").append(this.
		 * paygateglobalApi)
		 * .append("&amount=").append(String.valueOf(order.getTotal())).append(
		 * "&description=")
		 * .append("Test Order").append("&identifier=").append(order.getId()).append(
		 * "&phone=") .append(user.getTmoney().getPhoneNumber()).append(
		 * "&network=TOGOCEL&url=BASE_URL/checkout/payment");
		 */

		Long amount = new Long(String.valueOf(
				(new BigDecimal(transaction.getAmount())).setScale(
						Utils.getCurrencyDecimalPlaces(transaction.getCurrencyCode()), RoundingMode.FLOOR))
				.replace(".", ""));

		builder.append(this.paygateglobalPageUrl).append("?token=").append(this.paygateglobalApi).append("&amount=")
				.append(amount).append("&description=")
				.append("Commande No " + transaction.getId() + " chez " + genericService.getCompany("en") + " par "
						+ user.getName())
				.append("&identifier=").append(transaction.getId()).append("&phone=")
				.append(transaction.getPhone()).append("&network=TOGOCEL&url=BASE_URL/%23/checkout/payment");
		transaction.setPaygateGlobalPaymentUrl(builder.toString());
	}
	

	@Override
	public String secret(Transaction transaction) throws StripeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String retrievePublishableKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
