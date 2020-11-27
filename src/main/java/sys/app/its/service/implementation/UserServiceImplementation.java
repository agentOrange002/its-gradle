package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.UserDto;
import sys.app.its.entity.PasswordResetTokenEntity;
import sys.app.its.entity.RoleEntity;
import sys.app.its.entity.UserEntity;
import sys.app.its.exception.ApplicationServiceException;
import sys.app.its.exception.ErrorMessages;
import sys.app.its.repository.PasswordResetTokenRepository;
import sys.app.its.repository.RoleRepository;
import sys.app.its.repository.UserRepository;
import sys.app.its.security.UserPrincipal;
import sys.app.its.service.UserService;
import sys.app.its.utility.Utility;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {		
	
	private UserRepository userRepository;		
	private RoleRepository roleRepository;
	private Utility utility;	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public List<UserDto> allUsers() {
		ModelMapper modelmapper = new ModelMapper();
		List<UserDto> listDto = new ArrayList<UserDto>();
		List<UserEntity> listEntity = userRepository.findAll();
		for(UserEntity entity: listEntity) {
			listDto.add(modelmapper.map(entity, UserDto.class));
		}
		return listDto;
	}
	
	@Override
	public UserDto getUserById(String userId) {	
		UserEntity entity = userRepository.findByUserId(userId);	
		if(entity==null) throw new UsernameNotFoundException(userId);
		return new ModelMapper().map(entity,UserDto.class);
	}

	@Override
	public UserDto postUser(UserDto dto) {
		UserEntity entity = new ModelMapper().map(dto, UserEntity.class);
		entity.setUserId(utility.generateUserId(10));
		entity.setFullName(utility.generateFullName(entity.getFirstName(), entity.getMiddleName(), entity.getLastName(), entity.getSuffixName()));
		entity.setEmailVerificationStatus(false);
		entity.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		UserEntity saveEntity = userRepository.save(entity);		
		return new ModelMapper().map(saveEntity, UserDto.class);
	}

	@Override
	public UserDto getUser(String email) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)throw new UsernameNotFoundException(email);		
		BeanUtils.copyProperties(userEntity,returnValue);	
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new UserPrincipal(userEntity);
	}

	@Override
	public UserDto createUser(UserDto user) {
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new ApplicationServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

		UserEntity userEntity = new ModelMapper().map(user, UserEntity.class);
		RoleEntity roleUser = roleRepository.findByName("ROLE_USER");
		String publicUserId = utility.generateUserId(30);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		userEntity.setEmailVerificationToken(utility.generateEmailVerificationToken(publicUserId));
		userEntity.setEmailVerificationStatus(false);
		userEntity.setRoles(Arrays.asList(roleUser));
		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		returnValue = new ModelMapper().map(storedUserDetails, UserDto.class);
		//amazonSES.verifyEmail(returnValue);
		return returnValue;
	}

	@Override
	public UserDto getEmail(String email) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) throw new UsernameNotFoundException(email);		
		BeanUtils.copyProperties(userEntity,returnValue);	
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) throw new ApplicationServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)	throw new ApplicationServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setMiddleName(user.getMiddleName());
		userEntity.setLastName(user.getLastName());
		userEntity.setFullName(user.getFullName());
		UserEntity updatedUserEntity = userRepository.save(userEntity);
		return new ModelMapper().map(updatedUserEntity, UserDto.class);
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new ApplicationServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userEntity);
		
	}

	@Override
	public boolean verifyEmailToken(String token) {
		boolean returnValue = false;
		UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token);
		if (userEntity != null) {
			boolean hastokenExpired = Utility.hasTokenExpired(token);
			if (!hastokenExpired) {
				userEntity.setEmailVerificationToken(null);
				userEntity.setEmailVerificationStatus(Boolean.TRUE);
				userRepository.save(userEntity);
				returnValue = true;
			}
		}

		return returnValue;
	}

	@Override
	public boolean requestPasswordReset(String email) {
		boolean returnValue = false;
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			return returnValue;
		}
		String token = new Utility().generatePasswordResetToken(userEntity.getUserId());
		PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
		passwordResetTokenEntity.setToken(token);
		passwordResetTokenEntity.setUserDetails(userEntity);
		passwordResetTokenRepository.save(passwordResetTokenEntity);
		//returnValue = new AmazonSES().sendPasswordResetRequest(userEntity.getFirstName(), userEntity.getEmail(), token);
		return returnValue;
	}

	@Override
	public boolean resetPassword(String token, String password) {
		boolean returnValue = false;

		if (Utility.hasTokenExpired(token)) {
			return returnValue;
		}

		PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);

		if (passwordResetTokenEntity == null) {
			return returnValue;
		}

		// Prepare new password
		String encodedPassword = bCryptPasswordEncoder.encode(password);

		// Update User password in database
		UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
		userEntity.setEncryptedPassword(encodedPassword);
		UserEntity savedUserEntity = userRepository.save(userEntity);

		// Verify if password was saved successfully
		if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
			returnValue = true;
		}

		// Remove Password Reset token from database
		passwordResetTokenRepository.delete(passwordResetTokenEntity);

		return returnValue;
	}

	@Override
	public boolean authResetPassword(String id, String password) {
		boolean returnValue = false;
		String encodedPassword = bCryptPasswordEncoder.encode(password);

		UserEntity userEntity = userRepository.findByUserId(id);
		userEntity.setEncryptedPassword(encodedPassword);
		UserEntity savedUserEntity = userRepository.save(userEntity);

		// Verify if password was saved successfully
		if (savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
			returnValue = true;
		}

		return returnValue;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> returnList = new ArrayList<UserDto>();
		List<UserEntity> entityList = userRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		for (UserEntity entity : entityList) {
			UserDto dto = mapper.map(entity, UserDto.class);
			returnList.add(dto);
		}
		return returnList;
	}

	@Override
	public List<UserDto> getAllUsersByRole(String roleName) {
		RoleEntity role = roleRepository.findByName(roleName);
		List<UserDto> returnList = new ArrayList<UserDto>();
		List<UserEntity> entityList = userRepository.findByRoles(role);
		ModelMapper mapper = new ModelMapper();
		for (UserEntity entity : entityList) {
			UserDto dto = mapper.map(entity, UserDto.class);
			returnList.add(dto);
		}
		return returnList;
	}

}
