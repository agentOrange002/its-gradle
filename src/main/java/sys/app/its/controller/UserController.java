package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sys.app.its.dto.UserDto;
import sys.app.its.enums.RequestOperationName;
import sys.app.its.enums.RequestOperationStatus;
import sys.app.its.model.request.ResetPasswordRequestModel;
import sys.app.its.model.request.UserRequestModel;
import sys.app.its.model.response.OperationStatusModel;
import sys.app.its.model.response.UserResponseModel;
import sys.app.its.model.shortresponse.ShortUserResponseModel;
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
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponseModel updateUser(@PathVariable String id, @RequestBody UserRequestModel userDetails) {
		UserDto userDto = new ModelMapper().map(userDetails, UserDto.class);
		UserDto updatedUser = userService.updateUser(id, userDto);		
		return new ModelMapper().map(updatedUser, UserResponseModel.class);
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETED.name());
		userService.deleteUser(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}

	@PostMapping(path = "/{id}/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public OperationStatusModel resetPassword(@PathVariable String id, @RequestBody ResetPasswordRequestModel resetPassModel) {
		String password = resetPassModel.getConfirmedPassword();
		OperationStatusModel returnValue = new OperationStatusModel();
		boolean result = userService.authResetPassword(id, password);
		returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		if (result) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}
		return returnValue;
	}	
	
	@GetMapping(path = "/role/{roleName}", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ShortUserResponseModel> getAllUsersByRole(@PathVariable String roleName) {
		List<ShortUserResponseModel> returnList = new ArrayList<ShortUserResponseModel>();
		List<UserDto> dtoList = userService.getAllUsersByRole(roleName);
		ModelMapper mapper = new ModelMapper();
		for (UserDto dto : dtoList) {
			ShortUserResponseModel responseModel = mapper.map(dto, ShortUserResponseModel.class);
			returnList.add(responseModel);
		}
		return returnList;
	}
	
}
