package sys.app.its.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TicketDto implements Serializable {
	private static final long serialVersionUID = 3561800441893681802L;
	private String id;				
	private String ticketId;	
	private Date dateOpened;			
	private Date dateClosed;		
	private UserDto userticketDetails;
	private IssueDto issueTickets;	
}
