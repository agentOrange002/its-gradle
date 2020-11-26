package sys.app.its.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.IssueCategoryEntity;

@Repository
@Transactional
public interface IssueCategoryRepository extends JpaRepository<IssueCategoryEntity, Long> {
	IssueCategoryEntity findByName(String name);
	IssueCategoryEntity findByCategoryId(String categoryId);
}
