package sys.app.its.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import sys.app.its.model.shortresponse.ShortIssueResponseModel;
import sys.app.its.model.shortresponse.ShortUserResponseModel;

@Getter @Setter
public class TicketResponseModel {
	private String id;
	private String ticketId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateOpened;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateClosed;
	private ShortUserResponseModel userticketDetails;
	private ShortIssueResponseModel issueTickets;
}
