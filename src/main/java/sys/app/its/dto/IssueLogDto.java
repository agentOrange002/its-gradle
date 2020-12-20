package sys.app.its.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class IssueLogDto implements Serializable {
	private static final long serialVersionUID = -7880216597122242080L;
	private Long id;
	private String issueLogId;
	private String issueMessage;
	private Date logDate;	
	private UserDto issueLogUserDetails;
	private IssueDto issueDetails;
}
