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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import sys.app.its.dto.UserAddressDto;
import sys.app.its.entity.UserAddressEntity;
import sys.app.its.model.request.UserAddressRequestModel;
import sys.app.its.model.response.UserAddressResponseModel;
import sys.app.its.service.UserAddressService;

@Tag(name = "UserAddresses", description = "User Addresses REST API Service")
@AllArgsConstructor
@RestController
@RequestMapping({"/api/useraddresses"})
public class UserAddressController {
	
	private UserAddressService addressService;
	
	@Operation(summary = "All UserAddress", description = "Get all UserAddress", tags = "UserAddress")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserAddressEntity.class)))) })
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
	
	@Operation(summary = "UserAddress By AddressId", description = "Get UserAddress By AddressId", tags = "UserAddress")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserAddressEntity.class)))) })
	@GetMapping(path="/{addressId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAddressResponseModel getAddressById(@PathVariable String addressId) {
		UserAddressDto dto = addressService.getAddressById(addressId);
		return new ModelMapper().map(dto, UserAddressResponseModel.class);
	}
	
	@Operation(summary = "UserAddress with UserId", description = "Save UserAddress with UserId", tags = "UserAddress")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserAddressEntity.class)))) })
	@PostMapping(path="/{userId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAddressResponseModel saveAddress(@PathVariable String userId,@RequestBody UserAddressRequestModel model) {
		UserAddressDto dto = addressService.saveAddress(userId,new ModelMapper().map(model,UserAddressDto.class));
		return new ModelMapper().map(dto, UserAddressResponseModel.class);
	}
}
