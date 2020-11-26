package sys.app.its.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueDto implements Serializable {
	private static final long serialVersionUID = 5036503763438799517L;
	private String id;
	private String issueId;
	private String subject;
	private String description;
	private String reportedBy;
	private String emailProvided;
	private Date dateReported;
	private Date dateOpened;
	private Date dateClosed;
	private String issueStatus;
	private IssueCategoryDto category;
	private UserDto issueUserDetails;
	private UserDto supportUserDetails;
}
