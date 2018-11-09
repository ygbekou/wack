package com.qkcare.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.model.Admission;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.Bill;
import com.qkcare.model.BillPayment;
import com.qkcare.model.BillService;
import com.qkcare.model.DoctorOrder;
import com.qkcare.model.PackageService;
import com.qkcare.model.Payment;
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
		
		if (bill.getBillServices() != null) {
			for (BillService bs : bill.getBillServices()) {
				bs.setBill((Bill)toReturn);
				this.genericService.save(bs);
			}
		}
		
		if (bill.getBillPayments() != null) {
			for (BillPayment bp : bill.getBillPayments()) {
				if (bp.getAmount() != null && bp.getAmount() > 0) {
					bp.setBill((Bill)toReturn);
					this.genericService.save(bp);
				}
			}
		}
		
		return toReturn;
	}
	
	
	public BaseEntity findBill(Class cl, Long key, String itemLabel, Long itemId) {
		Bill bill = null;
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		if (key != null) {
			bill = (Bill) this.genericService.find(cl, key);
		} else {
			paramTupleList.add(Quartet.with("e." + itemLabel.toLowerCase() + ".id = ", "itemId", itemId + "", "Long"));
			String queryStr =  "SELECT e FROM Bill e WHERE 1 = 1"; 
			List<BaseEntity> bills = genericService.getByCriteria(queryStr, paramTupleList, null);
			if (bills.size() > 0) {
				bill = (Bill) bills.get(0);
			}
		}
		
		if (bill == null) {
			return bill;
		}
		
		paramTupleList.clear();
		paramTupleList.add(Quartet.with("e.bill.id = ", "billId", bill.getId() + "", "Long"));
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
		paramTupleList.add(Quartet.with("e.bill.id = ", "billId", bill.getId() + "", "Long"));
		queryStr =  "SELECT e FROM BillPayment e WHERE 1 = 1";
		List<BaseEntity> payments = genericService.getByCriteria(queryStr, paramTupleList, null);
		List<BillPayment> billPayments = new ArrayList<BillPayment>();
		
		for (BaseEntity entity : payments) {
			BillPayment billPayment = (BillPayment)entity;
			billPayment.setBill(null);
			billPayments.add(billPayment);
			bill.addPayment(billPayment.getAmount());
		}
		bill.setBillPayments(billPayments);
		
		return bill;
		
	}
	
	public BaseEntity findBillInitial(String itemLabel, String itemNumber) {
		Bill bill = new Bill();
		
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.id = ", "itemId", itemNumber + "", "Long"));
		String queryStr =  "SELECT e FROM " + itemLabel + " e WHERE 1 = 1";
		List<BaseEntity> items = genericService.getByCriteria(queryStr, paramTupleList, null);
		
		if (items.size() > 0) {
			if ("visit".equalsIgnoreCase(itemLabel.toUpperCase()))
				bill.setVisit((Visit)items.get(0));
			else 
				bill.setAdmission((Admission)items.get(0));
		} else {
			return bill;
		}
		Long packageId = null;
		if ("visit".equalsIgnoreCase(itemLabel) &&  bill.getVisit().getPckage() != null)
			packageId = bill.getVisit().getPckage().getId();
		else if ("admission".equalsIgnoreCase(itemLabel) &&  bill.getAdmission().getPckage() != null)
			packageId = bill.getAdmission().getPckage().getId();
		
		paramTupleList.clear();
		
		if (packageId != null) {
			paramTupleList.add(Quartet.with("e.pckage.id = ", "packageId", packageId + "", "Long"));
			queryStr =  "SELECT e FROM PackageService e WHERE 1 = 1";
			List<BaseEntity> packageServices = genericService.getByCriteria(queryStr, paramTupleList, null);
			
			for (BaseEntity entity : packageServices) {
				BillService billService = new BillService();
				billService.setServiceDate(bill.getVisit().getVisitDatetime());
				billService.setService(((PackageService)entity).getService());
				billService.setQuantity(((PackageService)entity).getQuantity());
				billService.setUnitAmount(((PackageService)entity).getRate());
				billService.setTotalAmount(billService.getQuantity() * billService.getUnitAmount());
				billService.setDiscountAmount(0d);
				billService.setDiscountPercentage(0d);
				billService.setNetAmount(billService.getQuantity() * billService.getUnitAmount());
				billService.setDoctor(bill.getVisit().getDoctor());
				bill.addBillService(billService);
			}
		}
		
		return bill;
		
	}
	
	public BaseEntity findPackage(Class cl, Long key) {
		com.qkcare.model.Package packge = (com.qkcare.model.Package) this.genericService.find(cl, key);
		
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.pckage.id = ", "packageId", key + "", "Long"));
		String queryStr =  "SELECT e FROM PackageService e WHERE 1 = 1";
		List<BaseEntity> services = genericService.getByCriteria(queryStr, paramTupleList, null);
		List<PackageService> packageServices = new ArrayList<PackageService>();
		
		for (BaseEntity entity : services) {
			PackageService packageService = (PackageService)entity;
			packageService.setPckage(null);
			packageServices.add(packageService);
		}
		packge.setPackageServices(packageServices);
		
		
		return packge;
		
	}
	
	@Transactional
	public BaseEntity save(BillPayment billPayment) {
		BillPayment existingBillPayment = null;
		Bill bill = null;
		Double existingAmount = null;
		
		if (billPayment.getId() != null) {
			existingBillPayment = (BillPayment) this.genericService.find(BillPayment.class, billPayment.getId());
			existingAmount = existingBillPayment.getAmount();
		}
		BaseEntity toReturn = this.genericService.save(billPayment);
		
		if (existingBillPayment != null && billPayment.getAmount() != existingAmount) {
			bill = (Bill) this.genericService.find(Bill.class, billPayment.getBill().getId());
			bill.removePayment(existingBillPayment.getAmount());
			bill.addPayment(billPayment.getAmount());
			this.genericService.save(bill);
		}
		
		return toReturn;
	}
}
