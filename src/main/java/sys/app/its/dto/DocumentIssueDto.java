package sys.app.its.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DocumentIssueDto implements Serializable {
	private static final long serialVersionUID = -1415854199159346704L;
	private String id;
	private String issueId;
	private String subject;
	private String description;
	private String reportedBy;
	private String emailProvided;
	private Date dateReported;
	private String category;
	private String issueUserDetails;		
	private String supportUserDetails;
	private Date dateOpened;
	private Date dateClosed;
	private String issueStatus;
}
