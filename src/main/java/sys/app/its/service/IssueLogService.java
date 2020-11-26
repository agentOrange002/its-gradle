package sys.app.its.service;

import java.util.List;
import sys.app.its.dto.IssueLogDto;

public interface IssueLogService {
	IssueLogDto saveIssueLog(IssueLogDto issueLog, String issueId, String userId);
	List<IssueLogDto> getIssueLogById(String issueId);
}
