package sys.app.its.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.UserAddressEntity;

@Repository
@Transactional
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {
	UserAddressEntity findByAddressId(String addressId);
}
