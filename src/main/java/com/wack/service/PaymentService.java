package com.wack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wack.domain.GenericChartDto;
import com.wack.domain.GenericVO;
import com.wack.model.BaseEntity;
import com.wack.model.stock.Payment;

@Service(value="paymentService")
public interface PaymentService {
	
	public BaseEntity save(Payment payment) throws Exception;
	public GenericChartDto getMonthlyPayments();
}
