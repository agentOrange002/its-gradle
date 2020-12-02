package sys.app.its.service.implementation;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.DashboardDto;
import sys.app.its.repository.IssueRepository;
import sys.app.its.repository.TaskRepository;
import sys.app.its.repository.TicketRepository;
import sys.app.its.service.DashboardService;

@AllArgsConstructor
@Service
public class DashboardServiceImplementation implements DashboardService {

	private IssueRepository issueRepository;
	private TicketRepository ticketRepository;
	private TaskRepository taskRepository;
	
	@Override
	public DashboardDto getDashboard() {
		DashboardDto returnDto = new DashboardDto();		
		returnDto.setTotalOpenedIssues(issueRepository.countByIssueStatus("OPENED"));
		returnDto.setTotalClosedIssues(issueRepository.countByIssueStatus("CLOSED"));
		returnDto.setTotalCreatedIssues(issueRepository.countByIssueStatus("CREATED"));
		
		returnDto.setTotalOpenedTickets(ticketRepository.countByDateOpenedNotNull());
		returnDto.setTotalClosedTickets(ticketRepository.countByDateClosedNotNull());
		
		returnDto.setTotalOpenedTasks(taskRepository.countByDateOpenedNotNull());
		returnDto.setTotalClosedTasks(taskRepository.countByDateClosedNotNull());
		return returnDto;
	}

}
