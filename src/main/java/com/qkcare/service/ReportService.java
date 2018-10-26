package com.qkcare.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qkcare.domain.RunReportVO;

@Service(value="reportService")
public interface ReportService {
	
	public List<String> getParameterNames(String reportFileName);
	
	public @ResponseBody String createReport(@RequestBody RunReportVO runReport) throws SQLException;
}
