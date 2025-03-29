package com.wack.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wack.domain.DataSet;
import com.wack.domain.GenericChartDto;
import com.wack.domain.GenericVO;
import com.wack.domain.StatisticCriteria;
import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.model.Transaction;
import com.wack.model.stock.ContractLabor;
import com.wack.model.stock.Payment;

@Service(value = "paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	GenericService genericService;

	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;

	@Autowired
	@Qualifier("paygateGlobalService")
	PaymentProcessingService paygateGlobalService;
	
	@Autowired
	@Qualifier("stripeService")
	PaymentProcessingService stripeService;
	
	@Autowired
	private EntityManager entityManager;
	
	private static String query = "SELECT EXP_YEAR, EXP_MONTH, SUM(AMOUNT), SUM(FUND) FROM (\r\n"
			+ "	SELECT YEAR(PAYMENT_DATE) EXP_YEAR, MONTH(PAYMENT_DATE) EXP_MONTH, AMOUNT AMOUNT, 0 FUND\r\n"
			+ "	FROM PAYMENT \r\n" 
			+ "	WHERE STATUS = 0\r\n" 
			+ "	UNION ALL \r\n"
			+ "	SELECT YEAR(PURCHASE_DATE) EXP_YEAR, MONTH(PURCHASE_DATE) EXP_MONTH, TOTAL_AMOUNT AMOUNT, 0 FUND\r\n"
			+ "	FROM PURCHASE \r\n" 
			+ "	WHERE STATUS = 0\r\n" 
			+ "	UNION ALL \r\n"
			+ "	SELECT YEAR(RECEPTION_DATE) EXP_YEAR, MONTH(RECEPTION_DATE) EXP_MONTH, 0 AMOUNT, AMOUNT FUND\r\n"
			+ "	FROM FUND \r\n" 
			+ "	WHERE STATUS = 0\r\n" 
			+ ") EXPENSES\r\n" 
			+ "GROUP BY EXP_YEAR, EXP_MONTH";
	
	private static String EXPENSES_DATA_SET_QUERY = "SELECT DATE_PART, SUM(ASSET) ASSET, SUM(EXPENSES) EXPENSES "
			+ "FROM ("
				+ "SELECT DATE_PART, 0 ASSET, SUM(EXP) EXPENSES " 
				+ "FROM ( "
				+ "		SELECT @@GRP_BY_STMT_PMT@@ DATE_PART, SUM(P.AMOUNT) EXP " 
				+ "		FROM PAYMENT P "
				+ "     WHERE P.STATUS = 0 " 
				+ "     @@PROJECT_SUB_QUERY@@ "
				+ "		@@DATE_CONDITION_PMT@@ "
				+ "		GROUP BY @@GRP_BY_STMT_PMT@@ " 
				+ "		UNION ALL "
				+ "		SELECT @@GRP_BY_STMT_PUR@@ DATE_PART, SUM(P.TOTAL_AMOUNT) EXP "
				+ "		FROM PURCHASE P "
				+ "		WHERE P.STATUS = 0 "
				+ "		@@PROJECT_SUB_QUERY@@ "
				+ "		@@DATE_CONDITION_PUR@@ "
				+ "		GROUP BY @@GRP_BY_STMT_PUR@@ "
				+ ") T " 
				+ "GROUP BY DATE_PART " 
			+ "UNION ALL "
				+ "SELECT DATE_PART, SUM(ASSET) ASSET, 0 EXPENSES " 
				+ "FROM ( "
				+ "		SELECT @@GRP_BY_STMT_FUN@@ DATE_PART, SUM(F.AMOUNT) ASSET " 
				+ "		FROM  FUND F "
				+ "     WHERE STATUS = 0 " 
				+ "     @@PROJECT_SUB_QUERY@@ " 
				+ "		@@DATE_CONDITION_FUN@@ "
				+ "		GROUP BY @@GRP_BY_STMT_FUN@@ " 
				+ ") T GROUP BY DATE_PART " 
			+ ") T2 "
			+ "GROUP BY DATE_PART ORDER BY 1 ";
	

	private List<Double[]> getDataSet(Long projectId, String dateCond, String grpByStmt) {

		List<Double[]> list = null;

		String dateCondPmt = dateCond.replaceAll("O.CREATE_DATE", "P.PAYMENT_DATE");
		String dateCondPur = dateCond.replaceAll("O.CREATE_DATE", "P.PURCHASE_DATE");
		String dateCondFun = dateCond.replaceAll("O.CREATE_DATE", "F.RECEPTION_DATE");
		String grpByStmtPmt = grpByStmt.replaceAll("O.CREATE_DATE", "P.PAYMENT_DATE");
		String grpByStmtPur = grpByStmt.replaceAll("O.CREATE_DATE", "P.PURCHASE_DATE");
		String grpByStmtFun = grpByStmt.replaceAll("O.CREATE_DATE", "F.RECEPTION_DATE");


		if ( projectId > 0) {
			list = entityManager.createNativeQuery(EXPENSES_DATA_SET_QUERY
					.replaceAll("@@PROJECT_SUB_QUERY@@", " AND PROJECT_ID = :projectId ")
					.replaceAll("@@GRP_BY_STMT_PMT@@", grpByStmtPmt)
					.replaceAll("@@GRP_BY_STMT_PUR@@", grpByStmtPur)
					.replaceAll("@@GRP_BY_STMT_FUN@@", grpByStmtFun)
					.replaceAll("@@DATE_CONDITION_PMT@@", dateCondPmt)
					.replaceAll("@@DATE_CONDITION_PUR@@", dateCondPur)
					.replaceAll("@@DATE_CONDITION_FUN@@", dateCondFun))
					.setParameter("projectId", projectId).getResultList();

		}
		
		return list;

	}
	
	public GenericChartDto getChartInfo(StatisticCriteria criteria) {
		
		String dateCond = getDateCondition(criteria.getPeriod());
		String grpByStmt = getGrpByStatement(criteria.getGrpBy());
		
		List<Double[]> list = this.getDataSet(criteria.getProjectId(), dateCond, grpByStmt);

		GenericChartDto result = new GenericChartDto();

		DataSet fundDataSet = new DataSet("Funds", "#9CCC65", "#7CB342");
		DataSet expenseDataSet = new DataSet("Expenses", "#42A5F5", "#1E88E5");
		DataSet balanceDataSet = new DataSet("Balances", "#d95a10", "#bd560d");
		
		result.addDataset(fundDataSet);
		result.addDataset(expenseDataSet);
		result.addDataset(balanceDataSet);
		
		for (Object[] objects : list) {
			result.addLabel(objects[0].toString());
			fundDataSet.addDataItem(Double.valueOf(objects[1].toString()));
			expenseDataSet.addDataItem(Double.valueOf(objects[2].toString()));
			balanceDataSet.addDataItem(Double.valueOf(objects[1].toString()) - Double.valueOf(objects[2].toString()));
		}

		return result;
	}

	
	
	private String getGrpByStatement(String grpBy) {
		String grpByStmt = null;
		if (grpBy.equals("hour")) {
			grpByStmt = " DATE_FORMAT(O.CREATE_DATE,'%H') ";
		} else if (grpBy.equals("weekday")) {
			grpByStmt = " CONCAT(DATE_FORMAT(O.CREATE_DATE,'%w'),'-', DATE_FORMAT(O.CREATE_DATE,'%a')) ";
		} else if (grpBy.equals("day")) {
			grpByStmt = " CONCAT(DATE_FORMAT(O.CREATE_DATE,'%d'),' ', DATE_FORMAT(O.CREATE_DATE,'%b')) ";
		} else if (grpBy.equals("week")) {
			grpByStmt = " DATE_FORMAT(O.CREATE_DATE,'%u') ";
		} else if (grpBy.equals("month")) {
			grpByStmt = " CONCAT(DATE_FORMAT(O.CREATE_DATE,'%y'),'-',DATE_FORMAT(O.CREATE_DATE,'%m')) ";
		} else if (grpBy.equals("year")) {
			grpByStmt = " DATE_FORMAT(O.CREATE_DATE,'%Y') ";
		}
		return grpByStmt;
	}
	
	private String getDateCondition(String period) {
		String dateCond = "";
		if (period.equals("day")) {
			dateCond = "  AND DATE(O.CREATE_DATE) =CURDATE() ";
		} else if (period.equals("week")) {
			dateCond = " AND YEARWEEK(DATE(O.CREATE_DATE),1) =YEARWEEK(CURDATE(),1)";
		} else if (period.equals("month")) {
			dateCond = " AND EXTRACT(YEAR_MONTH FROM DATE(O.CREATE_DATE))= EXTRACT(YEAR_MONTH FROM CURDATE()) ";
		} else if (period.equals("year")) {
			dateCond = " AND YEAR(DATE(O.CREATE_DATE))= YEAR(CURDATE()) ";
		} else if (period.equals("century")) {
			dateCond = " AND 1=1  ";
		} else if (period.equals("1month")) {
			dateCond = " AND DATE(O.CREATE_DATE) > NOW() - INTERVAL 1 MONTH ";
		} else if (period.equals("3months")) {
			dateCond = " AND DATE(O.CREATE_DATE) > NOW() - INTERVAL 3 MONTH ";
		} else if (period.equals("6months")) {
			dateCond = " AND DATE(O.CREATE_DATE) > NOW() - INTERVAL 6 MONTH ";
		} else if (period.equals("1year")) {
			dateCond = " AND DATE(O.CREATE_DATE) > NOW() - INTERVAL 12 MONTH ";
		} else if (period.equals("5years")) {
			dateCond = " AND DATE(O.CREATE_DATE) > NOW() - INTERVAL 60 MONTH ";
		} else if (period.equals("all")) {
			dateCond = " AND 1=1  ";
		}
		return dateCond;
	}
	
	@Transactional
	public BaseEntity save(Payment payment) throws Exception {
		Boolean isNew = payment.getId() == null;
		Payment pymt = null;
		if (payment.getContractLabor() == null) {
			pymt = (Payment) genericService.save(payment);
		} else {
			if (isNew && payment.getContractLabor() != null) {
				ContractLabor contractLabor = (ContractLabor) genericService.find(ContractLabor.class,
						payment.getContractLabor().getId());

				if (contractLabor.getBalance() < payment.getAmount()) {
					throw new Exception(String.format("Payment amount %s is greater than Labor balance %s",
							payment.getAmount(), contractLabor.getBalance()));
				}

				pymt = (Payment) genericService.save(payment);
				contractLabor.setPaid(contractLabor.getPaid() + pymt.getAmount());
				contractLabor.setBalance(contractLabor.getBalance() - pymt.getAmount());

				genericService.save(contractLabor);

				Company company = this.genericService.getCompany("EN");

				String emailMessage = String.format("Payment of %s was made to %s labor", pymt.getAmount(),
						contractLabor.getLabel());

//			mailSender.sendMail(company.getFromEmail(), company.getToEmail().split(","), 
//					"Payment Message from the system", emailMessage);
			}
		}
		return pymt;

	}

	public GenericChartDto getMonthlyPayments() {
		String query = "SELECT EXP_YEAR, EXP_MONTH, SUM(AMOUNT), SUM(FUND) FROM (\r\n"
				+ "	SELECT YEAR(PAYMENT_DATE) EXP_YEAR, MONTH(PAYMENT_DATE) EXP_MONTH, AMOUNT AMOUNT, 0 FUND\r\n"
				+ "	FROM PAYMENT \r\n" 
				+ "	WHERE STATUS = 0\r\n" 
				+ "	UNION ALL \r\n"
				+ "	SELECT YEAR(PURCHASE_DATE) EXP_YEAR, MONTH(PURCHASE_DATE) EXP_MONTH, TOTAL_AMOUNT AMOUNT, 0 FUND\r\n"
				+ "	FROM PURCHASE \r\n" 
				+ "	WHERE STATUS = 0\r\n" 
				+ "	UNION ALL \r\n"
				+ "	SELECT YEAR(RECEPTION_DATE) EXP_YEAR, MONTH(RECEPTION_DATE) EXP_MONTH, 0 AMOUNT, AMOUNT FUND\r\n"
				+ "	FROM FUND \r\n" 
				+ "	WHERE STATUS = 0\r\n" 
				+ ") EXPENSES\r\n" 
				+ "GROUP BY EXP_YEAR, EXP_MONTH";

		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		List<Object[]> list = this.genericService.getNativeByCriteria(query, paramTupleList, null, null);
		GenericChartDto result = new GenericChartDto();

		DataSet expenseDataSet = new DataSet("Expenses", "#42A5F5", "#1E88E5");
		DataSet fundDataSet = new DataSet("Funds", "#9CCC65", "#7CB342");
		result.addDataset(expenseDataSet);
		result.addDataset(fundDataSet);
		for (Object[] objects : list) {
			result.addLabel(objects[1].toString() + "-" + objects[0].toString());
			expenseDataSet.addDataItem(Double.valueOf(objects[2].toString()));
			fundDataSet.addDataItem(Double.valueOf(objects[3].toString()));
		}

		return result;
	}

	@Transactional
	public BaseEntity save(Transaction transaction) throws Exception {
		Boolean isNew = transaction.getId() == null;

		Transaction trans = (Transaction) genericService.save(transaction);

		if (isNew) {
			if ("FLOOZ".equals(trans.getPaymentMethod()) || "TMONEY".equals(trans.getPaymentMethod())) {
				paygateGlobalService.processPayment(transaction);
			} else {
				stripeService.processPayment(transaction);
			}
		}

		return trans;

	}

}
