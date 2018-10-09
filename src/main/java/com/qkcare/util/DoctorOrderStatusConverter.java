package com.qkcare.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qkcare.model.enums.DoctorOrderStatusEnum;

@Converter(autoApply = true)
public class DoctorOrderStatusConverter implements AttributeConverter<DoctorOrderStatusEnum, Long> {

	@Override
	public Long convertToDatabaseColumn(DoctorOrderStatusEnum enumValue) {
        switch (enumValue) {
            case PENDING:
                return 1L;
            case INPROGRESS:
                return 2L;
            case COMPLETED:
                return 3L;
            case CLOSED:
                return 4L;
            default:
                throw new IllegalArgumentException("Unknown" + enumValue);
        }
    }

	@Override
	public DoctorOrderStatusEnum convertToEntityAttribute(Long dbData) {
        switch (dbData.intValue()) {
            case 1:
                return DoctorOrderStatusEnum.PENDING;
            case 2:
                return DoctorOrderStatusEnum.INPROGRESS;
            case 3:
                return DoctorOrderStatusEnum.COMPLETED;
            case 4:
                return DoctorOrderStatusEnum.CLOSED;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }

}