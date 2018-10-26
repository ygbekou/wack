package com.qkcare.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qkcare.domain.RunReportVO;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.mis.Parameter;
import com.qkcare.model.mis.Reference;
import com.qkcare.model.mis.Report;
import com.qkcare.model.mis.ReportTree;
import com.qkcare.service.GenericService;
import com.qkcare.service.ReportService;

@RestController
@RequestMapping("/service/report")
@CrossOrigin
public class ReportController {

	private static final Logger LOGGER = Logger.getLogger(ReportController.class);

	@Autowired
	GenericService genericService;
	@Autowired
	ReportService reportService;
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/getActiveReports", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Report> getActiveReports() {
		LOGGER.info("Report list Requested");
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.status = ", "status", "1", "Integer"));
		String queryStr =  "SELECT e FROM Report e WHERE 1 = 1 ";
		List<Report> retList = (List)genericService.getByCriteria(queryStr, paramTupleList, null);
		Collections.sort(retList);
		return retList;
	}

	@RequestMapping(value = "/getActiveReportsWithCategory", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<ReportTree> getActiveReportsWithCategory() {
		LOGGER.info("Report list Requested");
		List<Report> retList = getActiveReports();
		
		List<ReportTree> reportTrees = new ArrayList<ReportTree>();
		
		boolean found = false;
		
		for (Report report : retList) {
			found = false;
			for (ReportTree reportTree : reportTrees) {
				if (reportTree.getData().equals(report.getCategory())) {
					reportTree.addChild(report.getDescription(), "", report.getId().toString());
					found = true;
					break;
				}
			}
			
			if (!found) {
				ReportTree repTr = new ReportTree(report.getCategory(), 
						report.getCategory(), "fa-folder-open", "fa-folder");
				reportTrees.add(repTr);
				repTr.addChild(report.getDescription(), "", report.getId().toString());
			}
		}
		
		return reportTrees;
	}

	
	@RequestMapping(value = "/report/{reportId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Report getById(@PathVariable("reportId") Long reportId) {
		LOGGER.info("Report Requested for id: " + reportId);
		Report report = (Report) genericService.find(Report.class, reportId);

		return report == null ? new Report() : report;
	}

	@RequestMapping(value = "/getParameters/{reportId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Parameter> getParameters(@PathVariable("reportId") Long reportId) {
		LOGGER.info("Parameter list Requested for report id: " + reportId);

		Report report = (Report) genericService.find(Report.class, reportId);

		List<String> parameterNames = reportService.getParameterNames(report.getName());

		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.name = ", "parameterNames", StringUtils.join(parameterNames, ','), "List<String>"));
		String queryStr =  "SELECT e FROM Parameter e WHERE 1 = 1 ";
		List<Parameter> parameters = (List)genericService.getByCriteria(queryStr, paramTupleList, null);

		for (Parameter param : parameters) {
			if (param.getParameterValues() != null && !param.getParameterValues().isEmpty()) {
				param.setValues(new ArrayList<Reference>());
				for (String val : Arrays.asList(param.getParameterValues().split(","))) {
					param.getValues().add(new Reference(val, val));
				}
			} else if (StringUtils.isNotBlank(param.getParameterSql())) {
				paramTupleList.clear();
				List<Object[]> objects = this.genericService.getNativeByCriteria(param.getParameterSql(), paramTupleList, null, null);

				if (objects != null && !objects.isEmpty()) {
					param.setValues(new ArrayList<Reference>());
					for (Object[] obj : objects) {
						Reference item = new Reference(obj[0].toString(), obj[1].toString());
						param.getValues().add(item);
					}
				}

			}
		}

		return parameters;
	}

	@RequestMapping(value = "/printReport", method = RequestMethod.POST, headers = "Accept=application/json")
	public RunReportVO printReport(@RequestBody RunReportVO runReport) throws SQLException {
		String reportName = null;
		if (runReport.getReportId() == null) {
			reportName = this.printReportByName(runReport);
		} else {
			Report report = (Report) genericService.find(Report.class, runReport.getReportId());
			runReport.setReportName(report.getName());
			reportName = reportService.createReport(runReport);
		}
		RunReportVO report = new RunReportVO();
		report.setReportName(reportName);
		return report;

	}
	
	//@RequestMapping(value = "/printReportByName", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/pdf")
	private String printReportByName(RunReportVO runReport) throws SQLException {
		String reportName = reportService.createReport(runReport);

		return reportName;

	}
}