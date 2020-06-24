package com.softenza.emarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softenza.emarket.domain.GenericChartDto;
import com.softenza.emarket.domain.GenericVO;
import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.stock.Payment;

@Service(value="paymentService")
public interface PaymentService {
	
	public BaseEntity save(Payment payment) throws Exception;
	public GenericChartDto getMonthlyPayments();
}
