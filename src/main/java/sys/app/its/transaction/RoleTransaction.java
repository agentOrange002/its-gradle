package sys.app.its.transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sys.app.its.entity.AuthorityEntity;
import sys.app.its.entity.RoleEntity;
import sys.app.its.repository.RoleRepository;

@Component
public class RoleTransaction {
	private final static Logger logger = LoggerFactory.getLogger(AuthorityTransaction.class);
	
	@Autowired
	RoleRepository roleRepository;
	
	@Transactional
	public Collection<RoleEntity> getRoles(List<String> list) {
		Collection<RoleEntity> result = new ArrayList<RoleEntity>();		
		for(String namelist: list) {
			RoleEntity entity = roleRepository.findByName(namelist);
			result.add(entity);
			logger.info("Get RoleEntity :"+entity.toString());
		}	
		return result;
	}
	
/*	@Transactional 
	private RoleEntity createRole( String name,Collection<AuthorityEntity> authorities) {
		RoleEntity role = roleRepository.findByName(name); 
		if (role == null) { 
			role = new RoleEntity(); 
			role.setName(name);
			role.setAuthorities(authorities);
			roleRepository.save(role); 
		} 
		return role; 
	}
	*/
	@Transactional 
	private RoleEntity createRole(String name,Collection<AuthorityEntity> authorities) { 
		RoleEntity resultEntity = new RoleEntity(); 
		RoleEntity role = roleRepository.findByName(name); 
		if (role == null) { 
			role = new RoleEntity(); 
			role.setName(name);
			role.setAuthorities(authorities); 
			resultEntity = roleRepository.save(role); 
		}
		else { 
			for (AuthorityEntity auth : authorities) { 
				if(!role.getAuthorities().contains(auth)) { 
					role.getAuthorities().add(auth); 
				}
			} 
			resultEntity = roleRepository.save(role); 
		} 
		return resultEntity; 
	}			 
			
}