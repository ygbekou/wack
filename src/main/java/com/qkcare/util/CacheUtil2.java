package com.qkcare.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Quartet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qkcare.domain.GenericVO;
import com.qkcare.service.GenericService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil2 implements InitializingBean {
	
	public static String SYMPTOM = "symptom";
	public static String ALLERGY = "allergy";
	public static String MEDICAL_HISTORY = "medicalhistory";
	public static String SOCIAL_HISTORY = "socialhistory";
	public static String DISCHARGE_REASON = "dischargereason";
	
	
	@Autowired 
	@Qualifier("genericService")
	GenericService genericService;
	
	public static CacheManager CACHE_MANAGER = null;
	
	public CacheUtil2() {
		
	}
	
	public List<GenericVO> getCategoryReferences(String query) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		List<Object[]> list = this.genericService.getNativeByCriteria(query, 
				paramTupleList, null);
		Map<GenericVO, List<GenericVO>> resultMap = new HashMap<>();
		List<GenericVO> results = new ArrayList<>();
		Map<Long, GenericVO> keyMap = new HashMap<Long, GenericVO>();
		
		for (Object[] objects : list) {
			Long cateoryId = new Long(objects[0].toString());
			String cateoryName = objects[1].toString();
			Long allergyId = new Long(objects[2].toString());
			String allergyName = objects[3].toString();
			if (keyMap.get(cateoryId) == null) {
				keyMap.put(cateoryId, new GenericVO(cateoryId, cateoryName));
			}
			
			GenericVO keyVO = keyMap.get(cateoryId);
			if (resultMap.get(keyVO) == null) {
				resultMap.put(keyVO, new ArrayList<>());
			}
			resultMap.get(keyVO).add(new GenericVO(allergyId, allergyName));
		}
		
		for (GenericVO vo : resultMap.keySet()) {
			vo.setChilds(resultMap.get(vo));
		}
		return new ArrayList(resultMap.keySet());
	}

	public void populateReferenceCaches(Cache cache, String query) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		List<Object[]> list = this.genericService.getNativeByCriteria(query, 
				paramTupleList, null);
		
		for (Object[] objects : list) {
			Long elementId = new Long(objects[0].toString());
			String elementName = objects[1].toString();
			cache.put(new Element(elementId, new GenericVO(elementId, elementName), true));
		}
		
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		CACHE_MANAGER = CacheManager.getInstance();
		
		CACHE_MANAGER.addCache(SYMPTOM);
		Cache symptomCache = CACHE_MANAGER.getCache(SYMPTOM);
		this.populateReferenceCaches(symptomCache, "SELECT C.CATEGORY_ID, C.NAME CAT_NAME, SYMPTOM_ID, S.NAME SYMP_NAME "
				+ "FROM SYMPTOM S "
				+ "JOIN CATEGORY C ON S.CATEGORY_ID = C.CATEGORY_ID "
				+ "WHERE S.STATUS = 0 AND C.STATUS = 0");
		
		CACHE_MANAGER.addCache(ALLERGY);
		Cache allergyCache = CACHE_MANAGER.getCache(ALLERGY);
		this.populateReferenceCaches(allergyCache, "SELECT C.CATEGORY_ID, C.NAME CAT_NAME, ALLERGY_ID, A.NAME ALL_NAME "
				+ "FROM ALLERGY A "
				+ "JOIN CATEGORY C ON A.CATEGORY_ID = C.CATEGORY_ID "
				+ "WHERE A.STATUS = 0 AND C.STATUS = 0");
		
		CACHE_MANAGER.addCache(MEDICAL_HISTORY);
		Cache medicalHistoryCache = CACHE_MANAGER.getCache(MEDICAL_HISTORY);
		this.populateReferenceCaches(medicalHistoryCache, "SELECT MEDICALHISTORY_ID, NAME FROM MEDICALHISTORY WHERE STATUS = 0");
		
		CACHE_MANAGER.addCache(SOCIAL_HISTORY);
		Cache socialHistoryCache = CACHE_MANAGER.getCache(SOCIAL_HISTORY);
		this.populateReferenceCaches(socialHistoryCache, "SELECT SOCIALHISTORY_ID, NAME FROM SOCIALHISTORY WHERE STATUS = 0");
		
		CACHE_MANAGER.addCache(DISCHARGE_REASON);
		Cache dischargeReasonCache = CACHE_MANAGER.getCache(DISCHARGE_REASON);
		this.populateReferenceCaches(dischargeReasonCache, "SELECT DISCHARGE_REASON_ID, NAME FROM DISCHARGE_REASON WHERE STATUS = 0");

	}
	

}
