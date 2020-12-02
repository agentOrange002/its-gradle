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
import sys.app.its.dto.IssueDto;
import sys.app.its.dto.UserDto;
import sys.app.its.enums.RequestOperationName;
import sys.app.its.enums.RequestOperationStatus;
import sys.app.its.model.request.AssignSupportRequestModel;
import sys.app.its.model.request.IssueRequestModel;
import sys.app.its.model.response.IssueResponseModel;
import sys.app.its.model.response.OperationStatusModel;
import sys.app.its.model.shortresponse.ShortUserResponseModel;
import sys.app.its.service.IssueService;
import sys.app.its.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping({"/api/issues"})
public class IssueController {

	private IssueService issueService;	
	private UserService userService;		

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<IssueResponseModel> getIssues() {
		List<IssueResponseModel> returnValue = new ArrayList<IssueResponseModel>();
		List<IssueDto> listDto = issueService.getIssueList();
		ModelMapper modelMapper = new ModelMapper();
		for (IssueDto dto : listDto) {
			returnValue.add(modelMapper.map(dto, IssueResponseModel.class));
		}
		return returnValue;
	}	

	@GetMapping(path = "/{issueId}", produces = MediaType.APPLICATION_JSON_VALUE )
	public IssueResponseModel getIssueByIssueId(@PathVariable String issueId) {
		IssueDto issueDto = issueService.getIssueByIssueId(issueId);	 
		return new ModelMapper().map(issueDto, IssueResponseModel.class);
	}

	@PostMapping(path = "/{id}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueResponseModel postIssue(@PathVariable String id, @RequestBody IssueRequestModel issueDetails) {
		UserDto userDto = userService.getUserByUserId(id);
		String email = userDto.getEmail();
		String name =  userDto.getFullName();
		/*
		 * String name = new StringBuilder(userEntity.getFirstName() + ' ' +
		 * userEntity.getLastName()).toString();
		 */
		IssueDto issuedto = new ModelMapper().map(issueDetails, IssueDto.class);
		issuedto.setEmailProvided(email);
		issuedto.setReportedBy(name);
		IssueDto savedIssue = issueService.saveIssue(issuedto, id);	
		return new ModelMapper().map(savedIssue, IssueResponseModel.class);
	}

	@PutMapping(path = "/{id}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueResponseModel updateIssue(@PathVariable String id, @RequestBody IssueRequestModel issueDetails) {
		IssueDto issuedto = new ModelMapper().map(issueDetails, IssueDto.class);
		IssueDto updatedIssue = issueService.updateIssue(id, issuedto);				
		return  new ModelMapper().map(updatedIssue, IssueResponseModel.class);	
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationStatusModel deleteIssue(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETED.name());
		issueService.deleteIssue(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}

	/*
	 * @PutMapping(path="/assignedsupport/{issueId}/{userId}", produces =
	 * {MediaType.APPLICATION_JSON_VALUE}) public OperationStatusModel
	 * assignedSupport(@PathVariable String issueId,@PathVariable String userId) {
	 * OperationStatusModel returnValue = new OperationStatusModel();
	 * returnValue.setOperationName(RequestOperationName.ASSIGNED_SUPPORT.name());
	 * issueService.assignSupport(issueId,userId);
	 * returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name()); return
	 * returnValue; }
	 */

	@PutMapping(path = "/assignedsupport", produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueResponseModel assignedSupport(@RequestBody AssignSupportRequestModel assignsupportDetails) {			
		String issueId = assignsupportDetails.getIssueId();
		String userId = assignsupportDetails.getUserId();
		String categoryName = assignsupportDetails.getCategoryName();		
		IssueDto dto = issueService.assignSupport(issueId, userId, categoryName);
		UserDto owner = dto.getIssueUserDetails();
		UserDto support = dto.getSupportUserDetails();
		ShortUserResponseModel ownerResponse = new ModelMapper().map(owner, ShortUserResponseModel.class);
		ShortUserResponseModel supportResponse = new ModelMapper().map(support, ShortUserResponseModel.class);		
		IssueResponseModel response  = new ModelMapper().map(dto, IssueResponseModel.class);
		response.setIssueUserDetails(ownerResponse);
		response.setSupportUserDetails(supportResponse);
		return response;
	}

	@PutMapping(path = "/ownedthisissue/{issueId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueResponseModel ownedthisIssue(@PathVariable String issueId, @PathVariable String userId) {
		IssueResponseModel response = new IssueResponseModel();
		IssueDto dto = issueService.ownedthisIssue(issueId, userId);
		UserDto userDto = dto.getIssueUserDetails();
		ShortUserResponseModel surm = new ModelMapper().map(userDto, ShortUserResponseModel.class);
		response = new ModelMapper().map(dto, IssueResponseModel.class);
		response.setIssueUserDetails(surm);
		return response;
	}
}
