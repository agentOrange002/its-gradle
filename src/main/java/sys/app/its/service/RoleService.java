package sys.app.its.service;

import java.util.Collection;
import java.util.List;

import sys.app.its.dto.RoleDto;
import sys.app.its.entity.AuthorityEntity;
import sys.app.its.entity.RoleEntity;

public interface RoleService {
	RoleDto saveRole(RoleDto roleDto);
	RoleEntity createRole(String name, Collection<AuthorityEntity> authorities);
	List<RoleDto> getAllRole();
}
