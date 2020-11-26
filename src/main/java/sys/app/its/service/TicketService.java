package sys.app.its.service;

import java.util.List;

import sys.app.its.dto.TicketDto;


public interface TicketService {
	TicketDto createTicket(String issueId,String userId);
	List<TicketDto> getAllTickets();	
	List<TicketDto> getAllTicketsByUserId(String userId);	
	List<TicketDto> getAllTicketsByIssueId(String issueId);	
	TicketDto closeTicket(String ticketId);
	TicketDto getTicketByTicketId(String ticketId);	
	List<TicketDto> checkOpenedByIssueId(String issueId);	
}
