package com.qkcare.model.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qkcare.domain.GenericVO;
import com.qkcare.util.CacheUtil;

import net.sf.ehcache.Element;

public enum DoctorOrderStatusEnum {

	PENDING(1),
    INPROGRESS(2),
    COMPLETED(3),
	CLOSED(4);

	private static Map<Integer, String> statusMap = new HashMap<>();
    private Integer status;
	
    DoctorOrderStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
    
    public String getDescription() {
    	if (statusMap.isEmpty()) {
    		this.populateMap();
    	}
    	return this.statusMap.get(status);
    }
    
    private void populateMap() {
    	Element ele = CacheUtil.CACHE_MANAGER.getCache(CacheUtil.DOCTOR_ORDER_STATUS).get("active");
		List<GenericVO> results = (List<GenericVO>) ele.getObjectValue();
		for (GenericVO genericVO : results) {
			this.statusMap.put(genericVO.getId().intValue(), genericVO.getName());
		}
    }
}
