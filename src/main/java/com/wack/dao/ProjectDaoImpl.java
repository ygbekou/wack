package com.wack.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.log4j.Logger;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wack.domain.project.Color;
import com.wack.domain.project.Dashboard;
import com.wack.domain.project.Data;
import com.wack.domain.project.DataSet;
import com.wack.domain.project.ProjectSearchCriteria;
import com.wack.model.Employee;
import com.wack.model.ProjectDesc;
import com.wack.util.Utils;

@SuppressWarnings("unchecked")
@Repository
public class ProjectDaoImpl implements ProjectDao {
	private static Logger LOGGER = Logger.getLogger(ProjectDaoImpl.class);

	private final static String PROJECT_QUERY = "SELECT P.PROJECT_ID, P.BUDGET, SUM(CONT) CONT, SUM(EXP) EXP, \r\n"
			+ "			SUM(NBR_CONT), SUM(NBR_EXP), SUM(MAX_CONT),\r\n"
			+ "			SUM(MIN_CONT), SUM(AVG_CONT), SUM(DUR), SUM(MAX_EXP), SUM(MIN_EXP) \r\n" + "FROM (\r\n"
			+ "	SELECT P.PROJECT_ID, P.BUDGET, SUM(T0.AMOUNT) CONT, 0 EXP, \r\n"
			+ "			COUNT(T0.TRANSACTION_ID) NBR_CONT, 0 NBR_EXP, MAX(T0.AMOUNT) MAX_CONT,\r\n"
			+ "			MIN(T0.AMOUNT) MIN_CONT, AVG(T0.AMOUNT) AVG_CONT, 5 DUR, 0 MAX_EXP, 0 MIN_EXP  \r\n"
			+ "				FROM PROJECT P \r\n"
			+ "				LEFT OUTER JOIN TRANSACTION T0 ON P.PROJECT_ID  = T0.PROJECT_ID AND T0.IO = 0\r\n"
			+ "				WHERE 1 = 1 @@CHART_COND@@ " + "	GROUP BY PROJECT_ID\r\n" + "	UNION ALL \r\n"
			+ "	SELECT P.PROJECT_ID, P.BUDGET, 0 CONT, SUM(T1.AMOUNT) EXP, \r\n"
			+ "			0 NBR_CONT, COUNT(T1.TRANSACTION_ID) NBR_EXP, 0 MAX_CONT,\r\n"
			+ "			0 MIN_CONT, 0 AVG_CONT, 0 DUR, MAX(T1.AMOUNT) MAX_EXP, MIN(T1.AMOUNT) MIN_EXP  \r\n"
			+ "				FROM PROJECT P \r\n"
			+ "				LEFT OUTER JOIN TRANSACTION T1 ON P.PROJECT_ID  = T1.PROJECT_ID AND T1.IO = 1\r\n"
			+ "				WHERE 1 = 1 @@CHART_COND@@ " + "	GROUP BY PROJECT_ID\r\n" + ") P\r\n"
			+ "GROUP BY PROJECT_ID";

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private GenericDao genericDao;

	public Dashboard getChart(ProjectSearchCriteria searchCriteria) {

		Long projectId = searchCriteria.getProjectId();

		Dashboard dash = new Dashboard();

		getDataSet(dash, projectId);

		return dash;

	}

	private void getDataSet(Dashboard dash, Long projectId) {

		List<Color> colors = new ArrayList<Color>();
		Color onlineColor = new Color("#ff5722", "#FFFFFF", "#ff5722", "#ff5722", "#fa6b3e", "#fa6b3e");
		Color storeColor = new Color("#283593", "#FFFFFF", "#283593", "#283593", "#3c468f", "#3c468f");
		colors.add(onlineColor);
		colors.add(storeColor);

		List<DataSet> ds = new ArrayList<DataSet>();
		dash.setDataSet(ds);

		DataSet dataSet = new DataSet();
		dataSet.setTag("TT");
		dataSet.setColors(colors);
		ds.add(dataSet);

		List<Double[]> list = null;

		StringBuilder queryBuilder = new StringBuilder(PROJECT_QUERY);

		if (projectId != null && projectId > 0) {
			queryBuilder = new StringBuilder(PROJECT_QUERY.replace("@@CHART_COND@@", "AND P.PROJECT_ID = :projectId "));
		}

		list = entityManager.createNativeQuery(queryBuilder.toString()).setParameter("projectId", projectId)
				.getResultList();

		List<Data> projectDatas = new ArrayList<Data>();
		List<String> projectLabels = new ArrayList<String>();

		Data budgetData = new Data();
		List<Double> budgetTotal = new ArrayList<Double>();
		budgetData.setData(budgetTotal);
		projectDatas.add(budgetData);

		Data contributionData = new Data();
		List<Double> contributionTotal = new ArrayList<Double>();
		contributionData.setData(contributionTotal);
		projectDatas.add(contributionData);

		Data expenseData = new Data();
		List<Double> expenseTotal = new ArrayList<Double>();
		expenseData.setData(expenseTotal);
		projectDatas.add(expenseData);

		dataSet.setData(projectDatas);
		dataSet.setLabels(projectLabels);

		for (Object[] obj : list) {
			projectLabels.add("Data");
			budgetTotal.add(Utils.getDoubleValue(obj[1]));
			contributionTotal.add(Utils.getDoubleValue(obj[2]));
			expenseTotal.add(Utils.getDoubleValue(obj[3]));
			dash.setBudget(Utils.getDoubleValue(obj[1]));
			dash.setContribution(Utils.getDoubleValue(obj[2]));
			dash.setExpenses(Utils.getDoubleValue(obj[3]));
			dash.setNbrContributions(Utils.getIntegerValue(obj[4]));
			dash.setNbrExpenses(Utils.getIntegerValue(obj[5]));
			dash.setMaxContribution(Utils.getDoubleValue(obj[6]));
			dash.setMinContribution(Utils.getDoubleValue(obj[7]));
			dash.setAvgContribution(Utils.getDoubleValue(obj[8]));
			dash.setDuration(Utils.getDoubleValue(obj[9]));
			dash.setMaxExpense(Utils.getDoubleValue(obj[10]));
			dash.setMinExpense(Utils.getDoubleValue(obj[11]));
		}

	}

	public List<Employee> getProjectEmployees(ProjectSearchCriteria searchCriteria) {
		StringBuilder queryBuilder = new StringBuilder("SELECT pu, e FROM ProjectUser as pu, Employee as e "
				+ "  WHERE pu.user = e.user AND pu.status = :status "
				+ "");
		
		if (searchCriteria.getProjectId() != null && searchCriteria.getProjectId() > 0) {
			queryBuilder = new StringBuilder(queryBuilder.append("AND pu.project.id = :projectId "));
		}
		
		if (searchCriteria.getManaging() != null && searchCriteria.getManaging() > 0) {
			queryBuilder = new StringBuilder(queryBuilder.append("AND e.managing = :managing "));
		}
		

		Query query = entityManager.createQuery(queryBuilder.toString())
				.setParameter("status", searchCriteria.getStatus());
	
		if (searchCriteria.getProjectId() != null && searchCriteria.getProjectId() > 0) {
			query.setParameter("projectId", searchCriteria.getProjectId());
		}
		
		if (searchCriteria.getManaging() != null && searchCriteria.getManaging() > 0) {
			query.setParameter("managing", searchCriteria.getManaging());
		}

		List<Object[]> list = query.getResultList();
		List<Employee> employees = new ArrayList<>();

		for (Object[] objects : list) {
			Employee e = (Employee) objects[1];
			employees.add(e);
		}

		return employees;

	}
	
	public ProjectDesc getProjectDesc(Long projectId, String lang) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.project.id = ", "projectId", projectId + "", "Integer"));
		paramTupleList.add(Quartet.with("e.language = ", "language", lang + "", "String"));
		
		List<ProjectDesc> projectDescs = (List) genericDao.getByCriteria("SELECT e FROM ProjectDesc e WHERE 1 = 1 ", paramTupleList, null);

		return (projectDescs.size() > 0) ? projectDescs.get(0) : null;
	}
	
	
}
