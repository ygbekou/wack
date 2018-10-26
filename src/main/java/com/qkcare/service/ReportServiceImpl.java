package com.qkcare.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.qkcare.domain.RunReportVO;
import com.qkcare.model.mis.Parameter;
import com.qkcare.util.Constants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@Service(value="reportService")
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private GenericService genericService;
	@Autowired
	DataSource datasource;
	
	public List<String> getParameterNames(String reportFileName) {

		// return Arrays.asList("Name", "Status");

		List<String> parameterNames = new ArrayList<String>();
		String reportName = null;
		Map<String, String> parameterValueMap = new HashMap<String, String>();
		Map<String, String> parameterClassMap = new HashMap<String, String>();

		try {
			Resource resource = resourceLoader.getResource("classpath:/reports/" + reportFileName + ".jrxml");
			InputStream reportStream = resource.getInputStream();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(reportStream);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("parameter");

			NodeList headerLst = doc.getElementsByTagName("jasperReport");
			for (int s = 0; s < headerLst.getLength(); s++) {
				Node fstNode = headerLst.item(s);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element parameterElmnt = (Element) fstNode;
					reportName = parameterElmnt.getAttribute("name");
				}
			}

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element parameterElmnt = (Element) fstNode;
					String parameterName = parameterElmnt.getAttribute("name");
					String parameterClassName = parameterElmnt.getAttribute("class");

					if (parameterName != null) {
						if (parameterName.equalsIgnoreCase("SUBREPORT_DIR"))
							parameterValueMap.put(parameterName, parameterName);
						else
							parameterNames.add(parameterName);
						parameterClassMap.put(parameterName, parameterClassName);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parameterNames;
	}
	
	
	public @ResponseBody String createReport(@RequestBody RunReportVO runReport) throws SQLException {
		try {

			Resource resource = resourceLoader
					.getResource("classpath:/reports/" + runReport.getReportName() + ".jasper");

			InputStream reportStream = resource.getInputStream();

			Map parameters = new HashMap();

			if (runReport.getParameters() != null) {
				for (Parameter p : runReport.getParameters()) {
					if (p.getName().contains("Id") || "Long".equals(p.getDataType())) {
						parameters.put(p.getName(), Long.parseLong(p.getValue()));
					} else {
						parameters.put(p.getName(), p.getValue());
					}
				}
			}
			parameters.put("SUBREPORT_DIR", resourceLoader.getResource("classpath:/reports").getFile().getAbsolutePath()
					+ java.io.File.separator);
			// this is for some reports needing pictures.

			parameters.put("USER_IMAGE_DIR", Constants.PIC_FOLDER + java.io.File.separator + "assets"
					+ java.io.File.separator + "images" + java.io.File.separator + "user" + java.io.File.separator);

			parameters.put("REPORT_LOCALE", Constants.LOCALE);

			OutputStream ouputStream = new ByteArrayOutputStream();
			
			Connection c = this.datasource.getConnection();

			byte[] reportBytes = JasperRunManager.runReportToPdf(reportStream, parameters, c);

			String reportName = runReport.getReportName() + "_" + System.currentTimeMillis() + ".pdf";

			FileOutputStream fileOuputStream = new FileOutputStream(Constants.REPORT_RESULT_FOLDER + reportName);
			fileOuputStream.write(reportBytes);
			fileOuputStream.close();

			return reportName;

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
