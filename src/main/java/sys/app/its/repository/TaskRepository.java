package sys.app.its.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.TaskEntity;
import sys.app.its.entity.TicketEntity;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
	
	@Query(value = "SELECT * FROM show_tasks_by_support_user(:userId);", nativeQuery = true)
	List<TaskEntity> findTasksUsingSupportId(@Param("userId") String userId);
	List<TaskEntity> findTasksByTicketDetails(TicketEntity ticketEntity);
	TaskEntity findByTaskId(String taskId);
	long countByDateOpenedNotNull();
	long countByDateClosedNotNull();
}

//@Query(value = "CALL show_tasks_by_support_user(:userId);", nativeQuery = true)