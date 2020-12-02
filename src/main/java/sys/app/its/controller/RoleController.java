package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sys.app.its.dto.RoleDto;
import sys.app.its.model.request.RoleRequestModel;
import sys.app.its.model.response.RoleResponseModel;
import sys.app.its.service.RoleService;

@AllArgsConstructor
@RestController
@RequestMapping({"/api/roles"})
public class RoleController {
	private RoleService roleService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RoleResponseModel postRole(@RequestBody RoleRequestModel requestModel) {		
		RoleDto roleDto = new ModelMapper().map(requestModel, RoleDto.class);
		RoleDto saveroleDto =  roleService.saveRole(roleDto);	
		return new ModelMapper().map(saveroleDto, RoleResponseModel.class);
	}
	
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<RoleResponseModel> getRoles() {
		List<RoleDto> listDto = roleService.getAllRole();
		List<RoleResponseModel> responseList = new ArrayList<RoleResponseModel>();
		ModelMapper mapper = new ModelMapper();
		for(RoleDto dto: listDto) {
			responseList.add(mapper.map(dto, RoleResponseModel.class));
		}
		return responseList;
	}	
}
