package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.TicketDto;
import sys.app.its.entity.IssueEntity;
import sys.app.its.entity.TicketEntity;
import sys.app.its.entity.UserEntity;
import sys.app.its.exception.ApplicationServiceException;
import sys.app.its.exception.ErrorMessages;
import sys.app.its.repository.IssueRepository;
import sys.app.its.repository.TicketRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.TicketService;
import sys.app.its.utility.Utility;

@AllArgsConstructor
@Service
public class TicketServiceImplementation implements TicketService {
	
	private TicketRepository ticketRepository;
	private IssueRepository issueRepository;
	private UserRepository userRepository;
	private Utility utils;	

	@Override
	public TicketDto createTicket(String issueId, String userId) {			
		if(issueRepository.findIssueByIssueId(issueId) == null) throw new ApplicationServiceException(ErrorMessages.ISSUE_NOT_FOUND.getErrorMessage());
		if(issueRepository.findByIssueIdAndSupportUserDetailsIsNull(issueId)!=null) throw new  ApplicationServiceException(ErrorMessages.ISSUE_HAS_NO_SUPPORT.getErrorMessage());	
		TicketEntity entity = new TicketEntity();
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueId);	
		UserEntity userEntity = userRepository.findByUserId(userId);
		entity.setTicketId(utils.generateTicketId(10));
		entity.setDateOpened(new Date());
		entity.setIssueTickets(issueEntity);
		entity.setUserticketDetails(userEntity);
		TicketEntity saveEntity = ticketRepository.save(entity);		
		return new ModelMapper().map(saveEntity, TicketDto.class);
	}


	@Override
	public List<TicketDto> getAllTickets() {
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();		
		List<TicketEntity> ticketsEntity = ticketRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();	
		for(TicketEntity entity: ticketsEntity) {
			
			ticketsDto.add(modelMapper.map(entity,TicketDto.class));
		}		
		return ticketsDto;
	}


	@Override
	public List<TicketDto> getAllTicketsByUserId(String userId) {
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();		
		UserEntity userEntity = userRepository.findByUserId(userId);
		List<TicketEntity> ticketsEntity = ticketRepository.findAllByUserticketDetails(userEntity);		
		ModelMapper modelMapper = new ModelMapper();	
		for(TicketEntity entity: ticketsEntity) {
			
			ticketsDto.add(modelMapper.map(entity,TicketDto.class));
		}		
		return ticketsDto;
	}


	@Override
	public List<TicketDto> getAllTicketsByIssueId(String issueId) {
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();		
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueId);
		//List<TicketEntity> ticketsEntity = ticketRepository.findAllByIssueTickets(issueEntity);	
		List<TicketEntity> ticketsEntity = ticketRepository.findAllByIssueTicketsAndDateClosed(issueEntity,null);		
		ModelMapper modelMapper = new ModelMapper();	
		for(TicketEntity entity: ticketsEntity) {
			
			ticketsDto.add(modelMapper.map(entity,TicketDto.class));
		}		
		return ticketsDto;
	}


	@Override
	public TicketDto closeTicket(String ticketId) {
		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketId);
		if(ticketEntity==null) throw new  ApplicationServiceException(ErrorMessages.TICKETID_NOT_FOUND.getErrorMessage());
		ticketEntity.setDateClosed(new Date());
		TicketEntity updateEntity = ticketRepository.save(ticketEntity);
		TicketDto returnDto = new ModelMapper().map(updateEntity, TicketDto.class);
		return returnDto;
	}


	@Override
	public TicketDto getTicketByTicketId(String ticketId) {
		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketId);
		if(ticketEntity==null) throw new  ApplicationServiceException(ErrorMessages.TICKETID_NOT_FOUND.getErrorMessage());
		TicketDto returnDto = new ModelMapper().map(ticketEntity, TicketDto.class);
		return returnDto;
	}


	@Override
	public List<TicketDto> checkOpenedByIssueId(String issueId) {
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();		
		IssueEntity issueEntity = issueRepository.findIssueByIssueId(issueId);
		List<TicketEntity> ticketsEntity = ticketRepository.findAllByIssueTicketsAndDateOpenedAndDateClosed(issueEntity,null,null);		
		ModelMapper modelMapper = new ModelMapper();	
		for(TicketEntity entity: ticketsEntity) {			
			ticketsDto.add(modelMapper.map(entity,TicketDto.class));
		}		
		return ticketsDto;
	}

}
