package sys.app.its.model.response;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import sys.app.its.model.shortresponse.ShortIssueResponseModel;
import sys.app.its.model.shortresponse.ShortUserResponseModel;

@Getter @Setter
public class IssueLogResponseModel {
	
	private String issueLogId;
	private String issueMessage;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date logDate;	
	
	//private ShortUserResponseModel userResponseModel;	
	//private ShortIssueResponseModel issueResponseModel;
	
	private ShortUserResponseModel issueLogUserDetails;
	private ShortIssueResponseModel issueDetails;
}
