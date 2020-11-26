package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.AuthorityDto;
import sys.app.its.dto.RoleDto;
import sys.app.its.entity.AuthorityEntity;
import sys.app.its.entity.RoleEntity;
import sys.app.its.entity.UserEntity;
import sys.app.its.repository.AuthorityRepository;
import sys.app.its.repository.RoleRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.AuthorityService;

@AllArgsConstructor
@Service
public class AuthorityServiceImplementation implements AuthorityService {
	
	private AuthorityRepository authorityRepository;	
	private RoleRepository roleRepository;	
	private UserRepository userRepository;

	@Override
	public AuthorityEntity createAuthority(String name) {
		AuthorityEntity authority = authorityRepository.findByName(name);
		if(authority == null) { 	
			authority = new AuthorityEntity();
			authority.setName(name);
			authorityRepository.save(authority); 
		}
		return authority;
	}

	@Override
	public List<RoleDto> getRolesByUser(String userId) {
		UserEntity user = userRepository.findByUserId(userId);
		List<RoleEntity> listEntity = roleRepository.findAllByUsers(user);
		List<RoleDto> resultList = new ArrayList<RoleDto>();
		ModelMapper mapper = new ModelMapper();
		for(RoleEntity entity: listEntity) {
			resultList.add(mapper.map(entity, RoleDto.class));
		}	
		return resultList;
	}

	@Override
	public List<AuthorityDto> getAuthoritiesByUser(String userid) {
		/*
		 * UserEntity user = userRepository.findByUserId(userid); List<RoleEntity>
		 * listEntity = roleRepository.findAllByUsers(user); //List<RoleDto> resultList
		 * = new ArrayList<RoleDto>(); List<AuthorityDto> authorityList = new
		 * ArrayList<AuthorityDto>();
		 * 
		 * ModelMapper mapper = new ModelMapper(); for(RoleEntity entity: listEntity) {
		 * //resultList.add(mapper.map(entity, RoleDto.class));
		 * 
		 * for(AuthorityEntity a: entity.getAuthorities()) {
		 * authorityList.add(mapper.map(a, AuthorityDto.class)); } } return
		 * authorityList;
		 */
		
		List<AuthorityEntity> list = authorityRepository.findAuthorizationsByUserId(userid);
		List<AuthorityDto> authorityDto = new ArrayList<AuthorityDto>();
		ModelMapper mapper = new ModelMapper();
		for(AuthorityEntity entity: list) {
			authorityDto.add(mapper.map(entity, AuthorityDto.class));
		}		
		return authorityDto;
	}

}
