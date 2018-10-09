package com.qkcare.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qkcare.model.enums.DoctorOrderStatusEnum;
import com.qkcare.model.enums.PrescriptionType;

@Converter(autoApply = true)
public class PrescriptionTypeConverter implements AttributeConverter<PrescriptionType, Long> {

	@Override
	public Long convertToDatabaseColumn(PrescriptionType enumValue) {
        switch (enumValue) {
            case NEW:
                return 1L;
            case OLD:
                return 2L;
            default:
                throw new IllegalArgumentException("Unknown" + enumValue);
        }
    }

	@Override
	public PrescriptionType convertToEntityAttribute(Long dbData) {
        switch (dbData.intValue()) {
            case 1:
                return PrescriptionType.NEW;
            case 2:
                return PrescriptionType.OLD;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }

}