package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import sys.app.its.dto.DocumentIssueDto;
import sys.app.its.entity.IssueEntity;
import sys.app.its.model.response.DocumentIssueResponseModel;
import sys.app.its.service.IssueService;

@Tag(name = "UserIssueDocuments", description = "UserIssueDocuments REST API Service")
@AllArgsConstructor
@RestController
@RequestMapping({ "/api/userdocuments" })
public class UserDocumentController {
	
	private IssueService issueService;
	
	@Operation(summary = "All User Issue Documents", description = "Get List of All User Issue Documents", tags = "UserIssueDocuments")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = IssueEntity.class)))) })
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
