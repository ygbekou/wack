package com.softenza.emarket.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softenza.emarket.domain.DataSet;
import com.softenza.emarket.domain.GenericChartDto;
import com.softenza.emarket.domain.GenericVO;
import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.Company;
import com.softenza.emarket.model.stock.ContractLabor;
import com.softenza.emarket.model.stock.Payment;


@Service(value="paymentService")
public class PaymentServiceImpl  implements PaymentService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;
	
	
	@Transactional
	public BaseEntity save(Payment payment) throws Exception {		
		Boolean isNew = payment.getId() == null;
		
		Payment pymt = (Payment) genericService.save(payment);
		
		if (isNew && payment.getContractLabor() != null) {
			ContractLabor contractLabor = (ContractLabor) genericService.find(ContractLabor.class, payment.getContractLabor().getId());
			
			if (contractLabor.getBalance() < pymt.getAmount()) {
				throw new Exception(String.format("Payment amount %s is greater than Labor balance %s", pymt.getAmount(), contractLabor.getBalance()));
			}
			
			contractLabor.setPaid(contractLabor.getPaid() + pymt.getAmount() );
			contractLabor.setBalance(contractLabor.getBalance() - pymt.getAmount() );
			
			genericService.save(contractLabor);
		
			Company company = this.genericService.getCompany("EN");
			
			String emailMessage = String.format("Payment of %s was made to %s labor", pymt.getAmount(), contractLabor.getLabel());
			
//			mailSender.sendMail(company.getFromEmail(), company.getToEmail().split(","), 
//					"Payment Message from the system", emailMessage);
		}
		
		return pymt;
		
	}
	
	
	

	public GenericChartDto getMonthlyPayments() {
		String query = 
				"SELECT EXP_YEAR, EXP_MONTH, SUM(AMOUNT), SUM(FUND) FROM (\r\n" + 
				"	SELECT YEAR(PAYMENT_DATE) EXP_YEAR, MONTH(PAYMENT_DATE) EXP_MONTH, AMOUNT AMOUNT, 0 FUND\r\n" + 
				"	FROM PAYMENT \r\n" + 
				"	WHERE STATUS = 0\r\n" + 
				"	UNION\r\n" + 
				"	SELECT YEAR(PURCHASE_DATE) EXP_YEAR, MONTH(PURCHASE_DATE) EXP_MONTH, TOTAL_AMOUNT AMOUNT, 0 FUND\r\n" + 
				"	FROM PURCHASE \r\n" + 
				"	WHERE STATUS = 0\r\n" + 
				"	UNION\r\n" + 
				"	SELECT YEAR(RECEPTION_DATE) EXP_YEAR, MONTH(RECEPTION_DATE) EXP_MONTH, 0 AMOUNT, AMOUNT FUND\r\n" + 
				"	FROM FUND \r\n" + 
				"	WHERE STATUS = 0\r\n" + 
				") EXPENSES\r\n" + 
				"GROUP BY EXP_YEAR, EXP_MONTH";
		
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		List<Object[]> list = this.genericService.getNativeByCriteria(query, 
				paramTupleList, null, null);
		GenericChartDto result = new GenericChartDto();
		
		DataSet expenseDataSet = new DataSet("Expenses", "#42A5F5", "#1E88E5");
		DataSet fundDataSet = new DataSet("Funds", "#9CCC65", "#7CB342");
		result.addDataset(expenseDataSet);
		result.addDataset(fundDataSet);
		for (Object[] objects : list) {
			result.addLabel(objects[1].toString() + "-" + objects[0].toString());
			expenseDataSet.addDataItem(Double.valueOf(objects[2].toString()));
			fundDataSet.addDataItem(Double.valueOf(objects[3].toString()));
		}
		
		return result;
	}

}
