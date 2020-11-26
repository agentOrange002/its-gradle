package sys.app.its.model.response;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import sys.app.its.model.shortresponse.ShortUserResponseModel;

@Getter @Setter
public class IssueResponseModel {
	private String id;
	private String issueId;
	private String subject;
	private String description;
	private String emailProvided;
	private String reportedBy;	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateReported;		
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateOpened;	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateClosed;	
	private String issueStatus;	
	private IssueCategoryResponseModel category;	
	private ShortUserResponseModel issueUserDetails;	
	private ShortUserResponseModel supportUserDetails;
	
}
