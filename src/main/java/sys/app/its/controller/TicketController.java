package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sys.app.its.dto.TicketDto;
import sys.app.its.model.response.TicketResponseModel;
import sys.app.its.service.TicketService;

@AllArgsConstructor
@RestController
@RequestMapping({ "/api/tickets" })
public class TicketController {
	private TicketService ticketService;
	
	@PostMapping(path = "/create/{issueId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketResponseModel createTicket(@PathVariable String issueId,@PathVariable String userId) {		
		TicketDto ticketDto = ticketService.createTicket(issueId,userId);		
		TicketResponseModel returnValue = new ModelMapper().map(ticketDto, TicketResponseModel.class);	
		return returnValue;
	}
	
	@GetMapping(path = "/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketResponseModel getTicketByTicketId(@PathVariable String ticketid){
		TicketDto dto = ticketService.getTicketByTicketId(ticketid);
		TicketResponseModel returnResponse = new ModelMapper().map(dto, TicketResponseModel.class);
		return returnResponse;
	}
	
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketResponseModel> getAllTickets(){
		List<TicketResponseModel> tickets = new ArrayList<TicketResponseModel>();
		List<TicketDto> ticketsDto = ticketService.getAllTickets();
		ModelMapper modelMapper = new ModelMapper();		
		for(TicketDto dto: ticketsDto)	{
			tickets.add(modelMapper.map(dto,TicketResponseModel.class));
		}
		return tickets;
	}
	
	@GetMapping(path = "/all/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketResponseModel> getTicketByUserId(@PathVariable String userId){
		List<TicketResponseModel> tickets = new ArrayList<TicketResponseModel>();
		List<TicketDto> ticketsDto = ticketService.getAllTicketsByUserId(userId);
		ModelMapper modelMapper = new ModelMapper();		
		for(TicketDto dto: ticketsDto)	{
			tickets.add(modelMapper.map(dto,TicketResponseModel.class));
		}
		return tickets;
	}
	
	@GetMapping(path = "/all/issue/{issueId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketResponseModel> getTicketByIssueId(@PathVariable String issueId){
		List<TicketResponseModel> tickets = new ArrayList<TicketResponseModel>();
		List<TicketDto> ticketsDto = ticketService.getAllTicketsByIssueId(issueId);
		ModelMapper modelMapper = new ModelMapper();		
		for(TicketDto dto: ticketsDto)	{
			tickets.add(modelMapper.map(dto,TicketResponseModel.class));
		}
		return tickets;
	}
	
	@GetMapping(path = "/all/checkopenedissue/{issueId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketResponseModel> checkedOpenedIssue(@PathVariable String issueId){
		List<TicketResponseModel> tickets = new ArrayList<TicketResponseModel>();
		List<TicketDto> ticketsDto = ticketService.checkOpenedByIssueId(issueId);
		ModelMapper modelMapper = new ModelMapper();		
		for(TicketDto dto: ticketsDto)	{
			tickets.add(modelMapper.map(dto,TicketResponseModel.class));
		}
		return tickets;
	}
	
	@PutMapping(path = "/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketResponseModel closeTicket(@PathVariable String ticketId){
		TicketDto dto = ticketService.closeTicket(ticketId);
		TicketResponseModel returnValue = new ModelMapper().map(dto, TicketResponseModel.class);
		return returnValue;
	}
}
