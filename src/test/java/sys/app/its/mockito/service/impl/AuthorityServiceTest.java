package sys.app.its.mockito.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sys.app.its.entity.AuthorityEntity;
import sys.app.its.repository.AuthorityRepository;
import sys.app.its.repository.RoleRepository;
import sys.app.its.service.implementation.AuthorityServiceImplementation;

class AuthorityServiceTest {
	
	@InjectMocks
	AuthorityServiceImplementation authorityService;
	
	@Mock
	AuthorityRepository authorityRepository;
	
	@Mock
	RoleRepository roleRepository;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testCreateAuthority() {		
		AuthorityEntity entity = new AuthorityEntity();		
		entity.setName("TEST");
		entity.setRoles(null);
		
		when(authorityRepository.findByName(anyString())).thenReturn(null);
		when(authorityRepository.save(any(AuthorityEntity.class))).thenReturn(entity);
		
		AuthorityEntity saveEntity = authorityService.createAuthority("TEST");
		
		assertNotNull(saveEntity);
		assertEquals(saveEntity.getName(),entity.getName());
		assertEquals(entity.toString(),saveEntity.toString());
	}

}
