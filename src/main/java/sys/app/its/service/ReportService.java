package sys.app.its.service;

public interface ReportService {
	byte[] generatePDF(String issueId);
	byte[] generateIssueInfoReport(String id);
}
