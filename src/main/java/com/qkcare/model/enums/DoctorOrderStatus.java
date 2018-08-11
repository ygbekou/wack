package com.qkcare.model.enums;

import java.util.ResourceBundle;

public enum DoctorOrderStatus {

	NEW("doctor_order.status.new"),
    PROGRESS("doctor_order.status.progress"),
    COMPLETE("doctor_order.status.complete");

    private String status;
	private ResourceBundle bundle;
	
    DoctorOrderStatus(String status) {
    	bundle = ResourceBundle.getBundle(status);
        this.status = bundle.getString(status);
    }

    public String getStatus() {
        return status;
    }
}
