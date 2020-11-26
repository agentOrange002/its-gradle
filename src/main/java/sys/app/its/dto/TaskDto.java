package sys.app.its.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskDto implements Serializable {
	private static final long serialVersionUID = -3935090951350237630L;
	private String id;
	private String taskId;
	private Date dateOpened;
	private Date dateClosed;
	private String subject;
	private String description;
	private TicketDto ticketDetails;	
	private UserDto usertaskDetails;
}
