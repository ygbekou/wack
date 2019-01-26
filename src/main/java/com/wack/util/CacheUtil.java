package com.wack.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.javatuples.Quartet;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wack.domain.GenericVO;
import com.wack.service.GenericService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Component
public class CacheUtil implements InitializingBean {
	
	public static String SYMPTOM = "symptom";
	public static String ALLERGY = "allergy";
	public static String MEDICAL_HISTORY = "medicalhistory";
	public static String SOCIAL_HISTORY = "socialhistory";
	public static String DISCHARGE_REASON = "dischargereason";
	public static String LAB_TEST_GROUP = "labTestGroup";
	public static String LAB_TEST = "labTest";
	public static String LAB_TEST_METHOD = "labTestMethod";
	public static String LAB_TEST_UNIT = "labTestUnit";
	public static String DOCTOR_ORDER_STATUS = "doctorOrderStatus";
	public static String DOCTOR_ORDER_TYPE = "doctorOrderType";
	public static String PRESCRIPTION_TYPE = "prescriptionType";
	
	
	@Autowired 
	@Qualifier("genericService")
	GenericService genericService;
	
	public static CacheManager CACHE_MANAGER = null;
	
	public CacheUtil() {
		
	}
	
	public List<GenericVO> getCategoryReferences(String query) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		List<Object[]> list = this.genericService.getNativeByCriteria(query, 
				paramTupleList, null, null);
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

	public List<GenericVO> getReferences(String query) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		List<Object[]> list = this.genericService.getNativeByCriteria(query, 
				paramTupleList, null, null);
		List<GenericVO> results = new ArrayList<>();
		
		for (Object[] objects : list) {
			Long elementId = new Long(objects[0].toString());
			String elementName = objects[1].toString();
			results.add(new GenericVO(elementId, elementName));
		}
		
		return results;
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		CACHE_MANAGER = CacheManager.getInstance();
		
	}
	
	
	private void addCacheToManager(String cacheKey, String sqlQuery, Function<String, List<GenericVO>> getCatg) {
		CACHE_MANAGER.addCache(cacheKey);
		Cache cache = CACHE_MANAGER.getCache(cacheKey);
		cache.put(new Element("active", getCatg.apply(sqlQuery), true));
	}
	

}
