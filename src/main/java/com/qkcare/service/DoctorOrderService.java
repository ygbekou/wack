package com.qkcare.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.DoctorOrder;


@Service(value="doctorOrderService")
public interface DoctorOrderService {
	
	public BaseEntity save(DoctorOrder doctorOrder);
	
}
