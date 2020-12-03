package sys.app.its.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PublicIssueRequestModel {
	private String subject;
	private String description;
	private String reportedBy;
	private String emailProvided;
}
