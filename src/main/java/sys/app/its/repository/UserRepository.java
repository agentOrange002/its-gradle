package sys.app.its.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.RoleEntity;
import sys.app.its.entity.UserEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByUserId(String userId);
	UserEntity findByEmail(String email);
	UserEntity findUserByEmailVerificationToken(String token);
	UserEntity deleteByUserId(String userId);
	UserEntity findById(String id);
	List<UserEntity> findByRoles(RoleEntity roleEntity);
}
