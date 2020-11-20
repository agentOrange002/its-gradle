package sys.app.its.service;

import java.util.List;

import sys.app.its.dto.UserDto;
 
public interface UserService {
	List<UserDto> allUsers();
	UserDto getUserById(String userId);
	UserDto postUser(UserDto dto);
}
