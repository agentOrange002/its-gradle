package sys.app.its.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.IssueEntity;
import sys.app.its.entity.TicketEntity;
import sys.app.its.entity.UserEntity;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
	List<TicketEntity> findAllByIssueTickets(IssueEntity issueEntity);	
	List<TicketEntity> findAllByUserticketDetails(UserEntity userEntity);
	TicketEntity findByTicketId(String ticketId);
	List<TicketEntity> findAllByIssueTicketsAndDateClosed(IssueEntity issueEntity, Date date);	
	List<TicketEntity> findAllByIssueTicketsAndDateOpenedAndDateClosed(IssueEntity issueEntity, Date dateopen,Date dateclose);	

	long countByDateOpenedNotNull();
	long countByDateClosedNotNull();
}
