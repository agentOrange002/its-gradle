package sys.app.its.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.IssueEntity;
import sys.app.its.entity.IssueLogEntity;

@Repository
@Transactional
public interface IssueLogRepository extends JpaRepository<IssueLogEntity, Long> {
	List<IssueLogEntity> findAllByIssueDetails(IssueEntity entity);
}
