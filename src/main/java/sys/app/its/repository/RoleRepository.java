package sys.app.its.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.RoleEntity;
import sys.app.its.entity.UserEntity;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByName(String name);
	List<RoleEntity> findAllByUsers(UserEntity user);
}
