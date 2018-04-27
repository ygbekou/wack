package com.qkcare.model.enums;

public enum RecurringFrequency {

	DAY("day"),
    WEEK("week"),
    SEMI_MONTH("semi_month"),
    MONTH("month"),
    YEAR("year"),
    UNKNOWN("");

    private String frequency;

    RecurringFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }
}
