package sys.app.its.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskRequestModel {
	private String subject;
	private String description;
	private String ticketid;
	private String userid;
}
