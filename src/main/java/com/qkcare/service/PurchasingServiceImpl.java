package com.qkcare.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.qkcare.model.Product;
import com.qkcare.model.Visit;
import com.qkcare.model.stocks.PurchaseOrder;
import com.qkcare.model.stocks.PurchaseOrderProduct;
import com.qkcare.model.stocks.ReceiveOrder;
import com.qkcare.model.stocks.ReceiveOrderProduct;

@Service(value="purchasingService")
public class PurchasingServiceImpl  implements PurchasingService {
	
	@Autowired
	GenericService genericService;
	
	@Transactional
	public BaseEntity save(PurchaseOrder purchaseOrder) {
		
		BaseEntity toReturn = this.genericService.save(purchaseOrder);
		
		for (PurchaseOrderProduct pop : purchaseOrder.getPurchaseOrderProducts()) {
			pop.setPurchaseOrder((PurchaseOrder)toReturn);
			this.genericService.save(pop);
		}
		
		return toReturn;
	}
	
	
	public BaseEntity findPurchaseOrder(Class cl, Long key) {
		PurchaseOrder purchaseOrder = (PurchaseOrder) this.genericService.find(cl, key);
		
		if (purchaseOrder != null) {
			List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
			paramTupleList.add(Quartet.with("e.purchaseOrder.id = ", "purchaseOrderId", key + "", "Long"));
			String queryStr =  "SELECT e FROM PurchaseOrderProduct e WHERE 1 = 1";
			List<BaseEntity> purchaseOrders = genericService.getByCriteria(queryStr, paramTupleList, null);
			List<PurchaseOrderProduct> purchaseOrderProducts = new ArrayList<PurchaseOrderProduct>();
			
			for (BaseEntity entity : purchaseOrders) {
				PurchaseOrderProduct purchaseOrderProduct = (PurchaseOrderProduct)entity;
				purchaseOrderProduct.setPurchaseOrder(null);
				purchaseOrderProducts.add(purchaseOrderProduct);
			}
			purchaseOrder.setPurchaseOrderProducts(purchaseOrderProducts);
		}
		return purchaseOrder;
		
	}
	
	@Transactional
	public BaseEntity save(ReceiveOrder receiveOrder) {
		
		BaseEntity toReturn = this.genericService.save(receiveOrder);
		
		for (ReceiveOrderProduct rop : receiveOrder.getReceiveOrderProducts()) {
			rop.setReceiveOrder((ReceiveOrder)toReturn);
			this.genericService.save(rop);
			
			if (receiveOrder.getStatus() == 2) {
				Product product = (Product) this.genericService.find(Product.class, rop.getProduct().getId());
				product.setQuantityInStock(product.getQuantityInStock() + rop.getQuantity());
				this.genericService.save(product);
			}
		}
		
		return toReturn;
	}
	
	public BaseEntity findInitialReceiveOrder(Class cl, Long purchaseOrderId) throws NumberFormatException, ParseException {
		String queryString = "SELECT PO.PURCHASE_ORDER_ID, PO.PURCHASE_ORDER_DATE, PO.SUPPLIER_ID, SP.NAME AS SP_NAME, POP.PRODUCT_ID, "
							+ "P.NAME AS P_NAME, POP.QUANTITY - IFNULL(SUM(ROP.QUANTITY), 0) AS QUANTITY "
							+ "FROM PURCHASE_ORDER PO "
							+ "JOIN PURCHASE_ORDER_PRODUCT POP ON PO.PURCHASE_ORDER_ID = POP.PURCHASE_ORDER_ID "
							+ "LEFT OUTER JOIN RECEIVE_ORDER RO ON PO.PURCHASE_ORDER_ID = RO.PURCHASE_ORDER_ID "
							+ "LEFT OUTER JOIN RECEIVE_ORDER_PRODUCT ROP ON RO.RECEIVE_ORDER_ID = ROP.RECEIVE_ORDER_ID "
							+ "JOIN SUPPLIER SP ON PO.SUPPLIER_ID = SP.SUPPLIER_ID "
							+ "JOIN PRODUCT P ON POP.PRODUCT_ID = P.PRODUCT_ID "
							+ "WHERE 1 = 1 "
							;
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("PO.PURCHASE_ORDER_ID = ", "purchaseOrderId", purchaseOrderId + "", "Long"));
		List<Object[]> list = this.genericService.getNativeByCriteria(queryString, paramTupleList, 
				" ORDER BY P.NAME ", " GROUP BY PO.PURCHASE_ORDER_ID, POP.PRODUCT_ID ");
		
		ReceiveOrder receiveOrder = new ReceiveOrder();
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		List<ReceiveOrderProduct> receiveOrderProducts = new ArrayList<ReceiveOrderProduct>();
		
		DateFormat format = new SimpleDateFormat("yyyyMMdd");

		for (Object[] objects : list) {
			receiveOrder.setPurchaseOrder(new PurchaseOrder(new Long(objects[0].toString()), format.parse(objects[1].toString()), 
					new Long(objects[2].toString()), String.valueOf(objects[3])));
			receiveOrderProducts.add(new ReceiveOrderProduct(null, null, 
					new Product(new Long(objects[4].toString()), String.valueOf(objects[5])), 
					Integer.valueOf(objects[6].toString()), Integer.valueOf(objects[6].toString()) ));
		}
		receiveOrder.setReceiveOrderProducts(receiveOrderProducts);
		
		return receiveOrder;
		
	}
	
}
