package com.qkcare.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.Bill;
import com.qkcare.model.BillPayment;
import com.qkcare.model.BillService;
import com.qkcare.model.DoctorOrder;
import com.qkcare.model.PackageService;
import com.qkcare.model.Visit;

@Service(value="billingService")
public class BillingServiceImpl  implements BillingService {
	
	@Autowired
	GenericService genericService;
	
	@Transactional
	public BaseEntity save(com.qkcare.model.Package pckage) {
		
		BaseEntity toReturn = this.genericService.save(pckage);
		
		for (PackageService ps : pckage.getPackageServices()) {
			ps.setPckage((com.qkcare.model.Package)toReturn);
			this.genericService.save(ps);
		}
		
		return toReturn;
	}
	
	@Transactional
	public BaseEntity save(Bill bill) {
		
		BaseEntity toReturn = this.genericService.save(bill);
		
		for (BillService bs : bill.getBillServices()) {
			bs.setBill((Bill)toReturn);
			this.genericService.save(bs);
		}
		
		return toReturn;
	}
	
	
	public BaseEntity findBill(Class cl, Long key) {
		Bill bill = (Bill) this.genericService.find(cl, key);
		
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.bill.id = ", "billId", key + "", "Long"));
		String queryStr =  "SELECT e FROM BillService e WHERE 1 = 1";
		List<BaseEntity> services = genericService.getByCriteria(queryStr, paramTupleList, null);
		List<BillService> billServices = new ArrayList<BillService>();
		
		for (BaseEntity entity : services) {
			BillService billService = (BillService)entity;
			billService.setBill(null);
			billServices.add(billService);
		}
		bill.setBillServices(billServices);
		
		
		paramTupleList.clear();
		paramTupleList.add(Quartet.with("e.bill.id = ", "billId", key + "", "Long"));
		queryStr =  "SELECT e FROM BillPayment e WHERE 1 = 1";
		List<BaseEntity> payments = genericService.getByCriteria(queryStr, paramTupleList, null);
		List<BillPayment> billPayments = new ArrayList<BillPayment>();
		
		for (BaseEntity entity : payments) {
			BillPayment billPayment = (BillPayment)entity;
			billPayment.setBill(null);
			billPayments.add(billPayment);
		}
		bill.setBillPayments(billPayments);
		
		return bill;
		
	}
	
	public BaseEntity findBillInitial(String itemNumber) {
		Bill bill = new Bill();
		
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.visitNumber = ", "visitNumber", itemNumber + "", "String"));
		String queryStr =  "SELECT e FROM Visit e WHERE 1 = 1";
		List<BaseEntity> items = genericService.getByCriteria(queryStr, paramTupleList, null);
		
		if (items.size() > 0) {
			bill.setVisit((Visit)items.get(0));
		}
		paramTupleList.clear();
		
		List<BillService> billServices = new ArrayList<BillService>();
		
		paramTupleList.add(Quartet.with("e.visit.id = ", "visitId", bill.getVisit().getId() + "", "Long"));
		queryStr =  "SELECT e FROM DoctorOrder e WHERE 1 = 1";
		List<BaseEntity> services = genericService.getByCriteria(queryStr, paramTupleList, null);
		
		for (BaseEntity entity : services) {
			BillService billService = new BillService();
			billService.setDefaultService((DoctorOrder)entity);
			billServices.add(billService);
		}
		bill.setBillServices(billServices);
		
		return bill;
		
	}
}
