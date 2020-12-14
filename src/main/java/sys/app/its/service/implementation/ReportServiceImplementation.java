package sys.app.its.service.implementation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sys.app.its.entity.IssueEntity;
import sys.app.its.repository.IssueRepository;
import sys.app.its.service.ReportService;

@Service
public class ReportServiceImplementation implements ReportService {
	
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	@Qualifier("newDataSource")
	DataSource datasource;	
	
	@Autowired
	ResourceLoader resourceLoader;

	Connection conn = null; 
	
	
	/*
	 * public Resource loadIssueReport() { return resourceLoader.getResource(
	 * "classpath:reports/issue_report.jrxml"); }
	 * 
	 * public Resource loadIssueInfo() { return resourceLoader.getResource(
	 * "classpath:reports/IssueInfo.jrxml"); }
	 */
	
	@Value("classpath:reports/issue_report.jrxml")
	Resource loadIssueReport;
	
	@Value("classpath:reports/IssueInfo.jrxml")
	Resource loadIssueInfo;
	
	@Override
	public byte[] generatePDF(String issueId) {
		byte[] bytes = null;
		/*
		 * DataSource dataSource = (DataSource)
		 * SpringApplicationContext.getBean("newDataSource");
		 */
		try {
			conn = datasource.getConnection();	
			//File file = ResourceUtils.getFile("classpath:reports/issue_report.jrxml");			
			//JasperDesign jdIIReport = JRXmlLoader.load(file);
			
			JasperDesign jdIIReport = JRXmlLoader.load(loadIssueReport.getInputStream());
			JasperReport jrIIReport = JasperCompileManager.compileReport(jdIIReport);
			Map<String, Object> param = new HashMap<>();
			param.put("issueid", issueId);
			JasperPrint jpIIReport = JasperFillManager.fillReport(jrIIReport, param, conn);		
			//jpIIReport.setName("/"+"Issue_Report_PDF");
			bytes = JasperExportManager.exportReportToPdf(jpIIReport);	
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}


	@Override
	public byte[] generateIssueInfoReport(String id) {
		byte[] bytes = null;
		
		IssueEntity entity = issueRepository.findIssueByIssueId(id);
		List<IssueEntity> collection = new ArrayList<IssueEntity>();
		collection.add(entity);		
		
		try {			
			//File file = ResourceUtils.getFile("classpath:reports/IssueInfo.jrxml");		
			JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(collection);
			JasperDesign jdReport = JRXmlLoader.load(loadIssueInfo.getInputStream());
			JasperReport jrReport = JasperCompileManager.compileReport(jdReport);			
			JasperPrint jpReport = JasperFillManager.fillReport(jrReport, null, data);
			bytes = JasperExportManager.exportReportToPdf(jpReport);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}	

}
