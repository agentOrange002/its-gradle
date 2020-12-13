package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import sys.app.its.dto.AuthorityDto;
import sys.app.its.dto.RoleDto;
import sys.app.its.model.response.AuthorityResponseModel;
import sys.app.its.model.response.RoleResponseModel;
import sys.app.its.service.AuthorityService;

@Tag(name = "Authorities", description = "Authorities REST API Service")
@AllArgsConstructor
@RestController
@RequestMapping({ "/api/authorities" })
public class AuthorityController {
	
	private AuthorityService authorityService;	

	@Operation(summary = "All Authorities By Roles UserId", description = "Get list of all Authorities By Roles UserId", tags = "Authorities")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorityResponseModel.class)))) })
	@GetMapping(path="/roles/{userid}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<RoleResponseModel> getRolesAuthoritiesByUser(@PathVariable String userid) {	
		List<RoleDto> listDto = authorityService.getRolesByUser(userid);
		List<RoleResponseModel> responseList = new ArrayList<RoleResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for(RoleDto dto: listDto) {
			responseList.add(mapper.map(dto, RoleResponseModel.class));
		}
		return responseList;		
	}
	
	@Operation(summary = "All Authorities By UserId", description = "Get list of all Authorities By UserId", tags = "Authorities")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorityResponseModel.class)))) })
	@GetMapping(path="/{userid}", produces = {MediaType.APPLICATION_JSON_VALUE})	
	public List<AuthorityResponseModel> getAuthoritiesByUser(@PathVariable String userid) {	
		List<AuthorityDto> listDto = authorityService.getAuthoritiesByUser(userid);
		List<AuthorityResponseModel> responseList = new ArrayList<AuthorityResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for(AuthorityDto dto: listDto) {
			responseList.add(mapper.map(dto, AuthorityResponseModel.class));
		}
		return responseList;		
	}
}
