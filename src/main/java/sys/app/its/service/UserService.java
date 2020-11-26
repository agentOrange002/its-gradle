package sys.app.its.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import sys.app.its.dto.UserDto;
 
public interface UserService extends UserDetailsService {
	List<UserDto> allUsers();
	UserDto getUserById(String userId);
	UserDto postUser(UserDto dto);
	UserDto getUser(String userName);
	
	UserDto createUser(UserDto user);
	UserDto getEmail(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	void deleteUser(String userId);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email);
	boolean resetPassword(String token, String password);	
	boolean authResetPassword(String id, String password);	
	List<UserDto> getAllUsers();
	List<UserDto> getAllUsersByRole(String roleName);
}
