package com.wack.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wack.domain.JobSearchCriteria;
import com.wack.model.JobPosition;
import com.wack.model.JobPositionDesc;
import com.wack.util.Utils;

@SuppressWarnings("unchecked")
@Repository
public class CareerDaoImpl implements CareerDao {
	private static Logger LOGGER = Logger.getLogger(CareerDaoImpl.class);

	private final static String JOB_POSITION_DESC_QUERY = "SELECT JPD.ID, JPD.JOB_POSITION_ID, TITLE, DESCRIPTION, JP.CREATE_DATE, "
			+ "COUNT(JA.ID) APPLI_CNT " + "FROM JOB_POSITION_DESC JPD "
			+ "JOIN JOB_POSITION JP ON JPD.JOB_POSITION_ID = JP.ID "
			+ "LEFT OUTER JOIN JOB_APPLI JA ON JP.ID = JA.JOB_POSITION_ID " + "WHERE 1 = 1 ";

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private GenericDao genericDao;

	@Override
	public List<JobPositionDesc> getJobPositionDescs(JobSearchCriteria searchCriteria) {

		List<JobPositionDesc> results = new ArrayList<JobPositionDesc>();

		try {

			StringBuilder sqlBuilder = new StringBuilder(JOB_POSITION_DESC_QUERY);

			if (searchCriteria.getJobPositionId() != null && searchCriteria.getJobPositionId() > 0) {
				sqlBuilder.append("AND JP.ID = ? ");
			}

			if (searchCriteria.getJobPositionDescId() != null && searchCriteria.getJobPositionDescId() > 0) {
				sqlBuilder.append("AND JPD.ID = ? ");
			}

			if (searchCriteria.getLanguage() != null) {
				sqlBuilder.append("AND JPD.LANGUAGE = ? ");
			}

			if (searchCriteria.getStatus() != null) {
				sqlBuilder.append("AND JP.STATUS = ? ");
			}

			sqlBuilder.append("GROUP BY JPD.ID, JPD.JOB_POSITION_ID, TITLE, DESCRIPTION, JP.CREATE_DATE ");
			sqlBuilder.append(" ORDER BY JPD.TITLE ");

			Query query = entityManager.createNativeQuery(sqlBuilder.toString());
			int i = 1;
			
			if (searchCriteria.getJobPositionId() != null && searchCriteria.getJobPositionId() > 0) {
				query.setParameter(i++, searchCriteria.getJobPositionId());
			}

			if (searchCriteria.getJobPositionDescId() != null && searchCriteria.getJobPositionDescId() > 0) {
				query.setParameter(i++, searchCriteria.getJobPositionDescId());
			}

			if (searchCriteria.getLanguage() != null) {
				query.setParameter(i++, searchCriteria.getLanguage());
			}

			if (searchCriteria.getStatus() != null) {
				query.setParameter(i++, searchCriteria.getStatus());
			}

			List<Object[]> list = query.getResultList();

			for (Object[] obj : list) {
				JobPositionDesc jpd = new JobPositionDesc();
				jpd.setId(Utils.getLongValue(obj[0]));
				jpd.setJobPosition(new JobPosition(Utils.getLongValue(obj[1])));
				jpd.setTitle(Utils.getStrValue(obj[2]));
				jpd.setDescription(Utils.getStrValue(obj[3]));
				jpd.setCreateDate(Utils.getDateValue(obj[4]));
				jpd.setJobAppliCnt(Utils.getIntegerValue(obj[5]));
				
				results.add(jpd);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

}
