package com.qkcare.model.enums;

public enum TransferType {

	DOCTOR("DOCTOR"),
    BED("BED");

    private String transferType;

    TransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferType() {
        return transferType;
    }
}
