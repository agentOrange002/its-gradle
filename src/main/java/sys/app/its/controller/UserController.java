package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sys.app.its.dto.UserDto;
import sys.app.its.model.request.UserRequestModel;
import sys.app.its.model.response.UserResponseModel;
import sys.app.its.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping({"/api/users"})
public class UserController {
	
	private UserService userService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserResponseModel> allUser(){
		ModelMapper mapper = new ModelMapper();
		List<UserResponseModel> response = new ArrayList<UserResponseModel>();
		List<UserDto> listDto = userService.allUsers();
		for(UserDto dto : listDto) {
			response.add(mapper.map(dto, UserResponseModel.class));
		}
		return response;
	}	
	
	@GetMapping(path="/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponseModel getUserByUserId(@PathVariable String userId) {
		UserDto dto = userService.getUserById(userId);
		return new ModelMapper().map(dto, UserResponseModel.class);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponseModel postUser(@RequestBody UserRequestModel model) {
		UserDto dto = userService.postUser(new ModelMapper().map(model, UserDto.class));
		return new ModelMapper().map(dto, UserResponseModel.class);
	}
	
}
