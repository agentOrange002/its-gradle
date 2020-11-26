package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.RoleDto;
import sys.app.its.entity.AuthorityEntity;
import sys.app.its.entity.RoleEntity;
import sys.app.its.repository.RoleRepository;
import sys.app.its.service.RoleService;

@AllArgsConstructor
@Service
public class RoleServiceImplementation implements RoleService {
	
	private RoleRepository roleRepository;

	@Override
	public RoleDto saveRole(RoleDto roleDto) {	
		RoleEntity roleEntity = new ModelMapper().map(roleDto, RoleEntity.class);
		RoleEntity saveroleEntity = roleRepository.save(roleEntity);	
		return new ModelMapper().map(saveroleEntity, RoleDto.class);
	}

	@Override
	public RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {
		RoleEntity role = roleRepository.findByName(name);
		if (role == null) {
			role = new RoleEntity();
			role.setName(name);
			role.setAuthorities(authorities);
			roleRepository.save(role);
		}
		return role;
	}

	@Override
	public List<RoleDto> getAllRole() {
		List<RoleEntity> listEntity = roleRepository.findAll();
		List<RoleDto> resultList = new ArrayList<RoleDto>();
		ModelMapper mapper = new ModelMapper();
		for(RoleEntity entity: listEntity) {
			resultList.add(mapper.map(entity, RoleDto.class));
		}	
		return resultList;
	}	

}
