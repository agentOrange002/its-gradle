package sys.app.its.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import sys.app.its.dto.IssueCategoryDto;
import sys.app.its.entity.IssueCategoryEntity;
import sys.app.its.model.request.IssueCategoryRequestModel;
import sys.app.its.model.response.IssueCategoryResponseModel;
import sys.app.its.service.IssueCategoryService;

@Tag(name = "IssueCategory", description = "IssueCategory REST API Service")
@AllArgsConstructor
@RestController
@RequestMapping({ "/api/issuecategories" })
public class IssueCategoryController {
	
	private IssueCategoryService categoryService;
	
	@Operation(summary = "All IssueCategory", description = "Get list of all IssueCategory", tags = "IssueCategory")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = IssueCategoryEntity.class)))) })
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<IssueCategoryResponseModel> getAllCategory() {
		List<IssueCategoryResponseModel> returnList = new ArrayList<IssueCategoryResponseModel>();
		List<IssueCategoryDto> listdto = categoryService.allCategory();
		ModelMapper mapper = new ModelMapper();
		for (IssueCategoryDto dto : listdto) {
			returnList.add(mapper.map(dto, IssueCategoryResponseModel.class));
		}
		return returnList;
	}
	
	@Operation(summary = "Post IssueCategory", description = "Save IssueCategory", tags = "IssueCategory")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = IssueCategoryEntity.class)))) })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueCategoryResponseModel createCategory(@RequestBody IssueCategoryRequestModel catDetails) {
		String name = catDetails.getName().toUpperCase();
		catDetails.setName(name);
		IssueCategoryDto dto = new ModelMapper().map(catDetails, IssueCategoryDto.class);
		IssueCategoryDto saveDto = categoryService.saveCategory(dto);		
		return new ModelMapper().map(saveDto, IssueCategoryResponseModel.class);
	}

	
	@Operation(summary = "Put IssueCategory", description = "Update IssueCategory", tags = "IssueCategory")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfull Operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = IssueCategoryEntity.class)))) })
	@PutMapping(path = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueCategoryResponseModel updateCategory(@PathVariable String categoryId,@RequestBody IssueCategoryRequestModel catDetails) {
		IssueCategoryDto dto = new ModelMapper().map(catDetails, IssueCategoryDto.class);
		IssueCategoryDto updateDto = categoryService.updateCategory(categoryId,dto);	
		return new ModelMapper().map(updateDto, IssueCategoryResponseModel.class);
	}
}