package sys.app.its.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DashboardDto implements Serializable {
	private static final long serialVersionUID = 5101292577715846155L;
	private long totalOpenedIssues;
	private long totalOpenedTickets;
	private long totalOpenedTasks;
	
	private long totalCreatedIssues;
	private long totalClosedIssues;
	private long totalClosedTickets;
	private long totalClosedTasks;
		
}
