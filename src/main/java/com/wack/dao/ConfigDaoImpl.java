package com.wack.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wack.domain.GenericSearchCriteria;
import com.wack.model.stock.Product;
import com.wack.util.Utils;

@SuppressWarnings("unchecked")
@Repository
public class ConfigDaoImpl implements ConfigDao {
	private static Logger LOGGER = Logger.getLogger(ConfigDaoImpl.class);

	private final static String PRODUCT_QUERY = "SELECT PD.PRODUCT_ID, PD.NAME, PD.DESCRIPTION, PCD.NAME PCD_NAME, PPCD.NAME PPCD_NAME "
			+ "FROM PRODUCT P "
			+ "JOIN PRODUCT_DESC PD ON PD.PRODUCT_ID = P.PRODUCT_ID "
			+ "JOIN PRD_CATEGORY PC ON P.PRD_CATEGORY_ID = PC.PRD_CATEGORY_ID "
			+ "JOIN PRD_CATEGORY_DESC PCD ON PC.PRD_CATEGORY_ID = PCD.PRD_CATEGORY_ID AND PD.LANGUAGE = PCD.LANGUAGE "
			+ "JOIN PRD_CATEGORY PPC ON PC.PARENT_ID = PPC.PRD_CATEGORY_ID "
			+ "JOIN PRD_CATEGORY_DESC PPCD ON PPC.PRD_CATEGORY_ID = PPCD.PRD_CATEGORY_ID AND PD.LANGUAGE = PPCD.LANGUAGE  ";

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private GenericDao genericDao;

	@Override
	public List<Product> getProducts(GenericSearchCriteria searchCriteria) {

		List<Product> results = new ArrayList<Product>();

		try {

			StringBuilder sqlBuilder = new StringBuilder(PRODUCT_QUERY);

			if (searchCriteria.getId() != null && searchCriteria.getId() > 0) {
				sqlBuilder.append("AND P.PRODUCT_ID = ? ");
			}

			if (searchCriteria.getPrdCategoryId() != null && searchCriteria.getPrdCategoryId() > 0) {
				sqlBuilder.append("AND P.PRD_CATEGORY_ID = ? ");
			}
			
			if (searchCriteria.getLanguage() != null) {
				sqlBuilder.append("AND PD.LANGUAGE = ? ");
			}

			if (searchCriteria.getStatus() != null) {
				sqlBuilder.append("AND P.STATUS = ? ");
			}

			sqlBuilder.append(" ORDER BY PD.NAME ");

			Query query = entityManager.createNativeQuery(sqlBuilder.toString());
			int i = 1;
			
			if (searchCriteria.getId() != null && searchCriteria.getId() > 0) {
				query.setParameter(i++, searchCriteria.getId());
			}
			
			if (searchCriteria.getPrdCategoryId() != null && searchCriteria.getPrdCategoryId() > 0) {
				query.setParameter(i++, searchCriteria.getPrdCategoryId());
			}

			if (searchCriteria.getLanguage() != null) {
				query.setParameter(i++, searchCriteria.getLanguage());
			}

			if (searchCriteria.getStatus() != null) {
				query.setParameter(i++, searchCriteria.getStatus());
			}

			List<Object[]> list = query.getResultList();

			for (Object[] obj : list) {
				Product p = new Product();
				p.setId(Utils.getLongValue(obj[0]));
				p.setName(Utils.getStrValue(obj[1]));
				p.setDescription(Utils.getStrValue(obj[2]));
				p.setCategoryName(Utils.getStrValue(obj[3]));
				p.setParentCategoryName(Utils.getStrValue(obj[4]));
				
				results.add(p);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

}
