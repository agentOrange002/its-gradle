package sys.app.its.service.impl.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sys.app.its.dto.UserDto;
import sys.app.its.entity.RoleEntity;
import sys.app.its.entity.UserEntity;
import sys.app.its.repository.RoleRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.service.implementation.UserServiceImplementation;
import sys.app.its.utility.Utility;

class UserServiceTest {

	@InjectMocks
	UserServiceImplementation userService;
	
	@Mock
	UserRepository userRepository;	
	
	@Mock
	RoleRepository roleRepository;
	
	@Mock
	Utility utility;	
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testGetUserById() {		
		UserEntity entity = new UserEntity();
		entity.setId(1L);
		entity.setFirstName("Nehemias");
		entity.setUserId("TESTID");
		entity.setEncryptedPassword("asdasdasdasdasd");
		when(userRepository.findByUserId(anyString())).thenReturn(entity);		
		UserDto dto = userService.getUserById("UIDH3CglYMIVT");
		assertNotNull(dto);
		assertEquals("Nehemias",dto.getFirstName());
	}
	
	@Test
	final void testGetUserById_UsernameNotFoundException() {
		when(userRepository.findByUserId(anyString())).thenReturn(null);
		assertThrows(UsernameNotFoundException.class, () -> {
			userService.getUserById("UIDH3CglYMIVT");
		});
	}
	
	@Test
	final void testCreateUser() {
		UserDto user = new UserDto();
		
		user.setFirstName("Jimboy");	
		user.setMiddleName("B");	
		user.setLastName("Orange");	
		user.setSuffixName("Jr");	
		user.setFullName(utility.generateFullName("Jimboy", "B", "Orange", "Jr"));	
		user.setEmail("jimboy02@gmail.com");			
		user.setPassword("password123");
		
		
		/*
		 * if (userRepository.findByEmail(user.getEmail()) != null) throw new
		 * ApplicationServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.
		 * getErrorMessage());
		 */

		UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
		RoleEntity roleUser = roleRepository.findByName("ROLE_USER");
		String publicUserId = utility.generateUserId(30);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		userEntity.setEmailVerificationToken(utility.generateEmailVerificationToken(publicUserId));
		userEntity.setEmailVerificationStatus(false);
		userEntity.setRoles(Arrays.asList(roleUser));
	
		
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		
		UserDto saveDto = userService.createUser(user);
		assertNotNull(saveDto);
		assertEquals(userEntity.getEmail(),saveDto.getEmail());
	}
	
}
