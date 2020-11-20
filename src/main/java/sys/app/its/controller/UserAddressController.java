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
import sys.app.its.dto.UserAddressDto;
import sys.app.its.model.request.UserAddressRequestModel;
import sys.app.its.model.response.UserAddressResponseModel;
import sys.app.its.service.UserAddressService;

@AllArgsConstructor
@RestController
@RequestMapping({"/users/addresses"})
public class UserAddressController {
	
	private UserAddressService addressService;
	
	@GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserAddressResponseModel> allAddress(){
		ModelMapper mapper = new ModelMapper();
		List<UserAddressResponseModel> response = new ArrayList<UserAddressResponseModel>();
		List<UserAddressDto> listDto = addressService.allAddress();
		for(UserAddressDto dto: listDto) {
			response.add(mapper.map(dto, UserAddressResponseModel.class));
		}
		return response;
	}
	
	@GetMapping(path="/{addressId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAddressResponseModel getAddressById(@PathVariable String addressId) {
		UserAddressDto dto = addressService.getAddressById(addressId);
		return new ModelMapper().map(dto, UserAddressResponseModel.class);
	}
	
	@PostMapping(path="/save/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAddressResponseModel saveAddress(@PathVariable String userId,@RequestBody UserAddressRequestModel model) {
		UserAddressDto dto = addressService.saveAddress(userId,new ModelMapper().map(model,UserAddressDto.class));
		return new ModelMapper().map(dto, UserAddressResponseModel.class);
	}
}
