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
import sys.app.its.dto.IssueDto;
import sys.app.its.dto.IssueLogDto;
import sys.app.its.dto.UserDto;
import sys.app.its.model.request.IssueLogRequestModel;
import sys.app.its.model.response.IssueLogResponseModel;
import sys.app.its.model.shortresponse.ShortIssueResponseModel;
import sys.app.its.model.shortresponse.ShortUserResponseModel;
import sys.app.its.service.IssueLogService;

@AllArgsConstructor
@RestController
@RequestMapping({"/api/issues/logs"})
public class IssueLogController {
	
	private IssueLogService issueLogService;

	@PostMapping(path = "/{issueId}/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueLogResponseModel postIssueLog(@PathVariable String issueId, @PathVariable String userId, @RequestBody IssueLogRequestModel requestdetails) {
		IssueLogResponseModel returnValue = new IssueLogResponseModel();
		IssueLogDto dto = new ModelMapper().map(requestdetails, IssueLogDto.class);
		IssueLogDto saveDto = issueLogService.saveIssueLog(dto, issueId, userId);
		UserDto userDto = saveDto.getUserDto();
		IssueDto issueDto = saveDto.getIssueDto();
		ShortIssueResponseModel irm = new ModelMapper().map(issueDto, ShortIssueResponseModel.class);
		ShortUserResponseModel urm = new ModelMapper().map(userDto, ShortUserResponseModel.class);
		returnValue = new ModelMapper().map(saveDto, IssueLogResponseModel.class);
		returnValue.setIssueResponseModel(irm);
		returnValue.setUserResponseModel(urm);
		return returnValue;
	}

	@GetMapping(path = "/{issueId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IssueLogResponseModel> getIssueLogByIssueID(@PathVariable String issueId) {
		List<IssueLogResponseModel> returnValue = new ArrayList<IssueLogResponseModel>();
		List<IssueLogDto> listdto = issueLogService.getIssueLogById(issueId);
		ModelMapper modelMapper = new ModelMapper();
		for (IssueLogDto ild : listdto) {
			IssueLogResponseModel ilrm = modelMapper.map(ild, IssueLogResponseModel.class);

			ModelMapper mm = new ModelMapper();
			UserDto udto = ild.getUserDto();
			IssueDto idto = ild.getIssueDto();

			ShortUserResponseModel surm = mm.map(udto, ShortUserResponseModel.class);
			ShortIssueResponseModel irm = mm.map(idto, ShortIssueResponseModel.class);

			ilrm.setUserResponseModel(surm);
			ilrm.setIssueResponseModel(irm);
			returnValue.add(ilrm);
		}
		return returnValue;
	}
}
