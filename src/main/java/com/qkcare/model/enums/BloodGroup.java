package com.qkcare.model.enums;

public enum BloodGroup {

	A_PLUS("A+"),
    A_MOINS("A-"),
    B_PLUS("B+"),
    B_MOINS("B-"),
    O_PLUS("O+"),
    O_MOINS("O-"),
    AB_PLUS("AB+"),
    AB_MOINS("AB-");

    private String bloodGroup;

    BloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
}
