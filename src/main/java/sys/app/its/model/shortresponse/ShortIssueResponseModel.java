package sys.app.its.model.shortresponse;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShortIssueResponseModel {
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
}
