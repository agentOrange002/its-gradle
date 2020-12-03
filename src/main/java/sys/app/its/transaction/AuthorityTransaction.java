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
import sys.app.its.repository.AuthorityRepository;

@Component
public class AuthorityTransaction {
	private final static Logger logger = LoggerFactory.getLogger(AuthorityTransaction.class);
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Transactional
	public Collection<AuthorityEntity> getAuthorities(List<String> list) {
		Collection<AuthorityEntity> result = new ArrayList<AuthorityEntity>();		
		for(String namelist: list) {
			AuthorityEntity entity = authorityRepository.findByName(namelist);
			result.add(entity);
			logger.info("Get AuthorityEntity :"+entity.toString());
		}	
		return result;
	}
	
	@Transactional
	private AuthorityEntity createAuthority(String name) 	{	  
		AuthorityEntity authority = authorityRepository.findByName(name); 
		if (authority == null)	{ 
			authority = new AuthorityEntity();
			authority.setName(name);
			authorityRepository.save(authority); 
		} 
		return authority; 
	}
}