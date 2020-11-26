package sys.app.its.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignSupportRequestModel {
	private String issueId;
	private String userId;
	private String categoryName;
}
