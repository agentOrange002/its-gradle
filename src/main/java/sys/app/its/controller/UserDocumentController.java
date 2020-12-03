package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sys.app.its.dto.DocumentIssueDto;
import sys.app.its.model.response.DocumentIssueResponseModel;
import sys.app.its.service.IssueService;

@RestController
@RequestMapping({ "/api/userdocuments" })
public class UserDocumentController {
	
	@Autowired
	IssueService issueService;
	
	@GetMapping(path = "/issues",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DocumentIssueResponseModel> getIssueDocuments(@RequestParam("email") String email){
		List<DocumentIssueResponseModel> returnList= new ArrayList<DocumentIssueResponseModel>();
		List<DocumentIssueDto> listDto = issueService.getIssueDocuments(email);
		ModelMapper modelMapper = new ModelMapper();
		for (DocumentIssueDto dto : listDto) {
			returnList.add(modelMapper.map(dto, DocumentIssueResponseModel.class));
		}	
		return returnList;
	}
}
