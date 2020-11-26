package sys.app.its.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.IssueEntity;
import sys.app.its.entity.UserEntity;

@Repository
@Transactional
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
	/* Using Stored Procedure
	 * @Query(value = "CALL FIND_CARS_AFTER_YEAR(:year_in);", nativeQuery = true)
	 * List<Car> findCarsAfterYear(@Param("year_in") Integer year_in);
	 */
	
	IssueEntity findIssueByIssueId(String issueId);
	IssueEntity findIssueByIssueIdAndIssueUserDetails(String issueId, UserEntity user);	
	long countByIssueStatus(String IssueStatus);	
	IssueEntity findByIssueIdAndSupportUserDetailsIsNull(String issueId);	
	List<IssueEntity> findByEmailProvided(String emailProvided);
}
