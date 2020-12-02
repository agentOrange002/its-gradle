package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sys.app.its.dto.AuthorityDto;
import sys.app.its.dto.RoleDto;
import sys.app.its.model.response.AuthorityResponseModel;
import sys.app.its.model.response.RoleResponseModel;
import sys.app.its.service.AuthorityService;

@AllArgsConstructor
@RestController
@RequestMapping({ "/api/users/authorities" })
public class AuthorityController {
	
	private AuthorityService authorityService;	

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
