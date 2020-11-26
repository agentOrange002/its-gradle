package sys.app.its.service;

import java.util.List;

import sys.app.its.dto.DocumentIssueDto;
import sys.app.its.dto.IssueDto;

public interface IssueService {
	boolean publicSaveIssue(IssueDto issue);	
	IssueDto saveIssue(IssueDto issue,String userId);
	IssueDto getIssueByIssueId(String issueId);
	IssueDto updateIssue(String issueId, IssueDto issue);
	void deleteIssue(String issueId);
	List<IssueDto> getIssueList();	
	IssueDto assignSupport(String issueId, String userId, String categoryName);
	IssueDto ownedthisIssue(String issueId, String userId);
	List<DocumentIssueDto> getIssueDocuments(String emailProvided);
}
