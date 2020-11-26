package sys.app.its.service;

import java.util.List;

import sys.app.its.entity.AuthorityEntity;
import sys.app.its.dto.RoleDto;
import sys.app.its.dto.AuthorityDto;

public interface AuthorityService {
	AuthorityEntity createAuthority(String name);	
	List<RoleDto> getRolesByUser(String userId);
	List<AuthorityDto> getAuthoritiesByUser(String userId);
}
