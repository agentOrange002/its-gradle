package sys.app.its.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import sys.app.its.model.shortresponse.ShortUserResponseModel;

@Getter @Setter
public class TaskResponseModel {
	
	private String id;

	private String taskId;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateOpened;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateClosed;	

	private String subject;

	private String description;

	private TicketResponseModel ticketDetails;
	
	private ShortUserResponseModel usertaskDetails;
}
