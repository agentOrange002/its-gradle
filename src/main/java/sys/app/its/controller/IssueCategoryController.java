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

import lombok.AllArgsConstructor;
import sys.app.its.dto.IssueCategoryDto;
import sys.app.its.model.request.IssueCategoryRequestModel;
import sys.app.its.model.response.IssueCategoryResponseModel;
import sys.app.its.service.IssueCategoryService;

@AllArgsConstructor
@RestController
@RequestMapping({ "/api/issuecategories" })
public class IssueCategoryController {
	
	private IssueCategoryService categoryService;

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

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueCategoryResponseModel createCategory(@RequestBody IssueCategoryRequestModel catDetails) {
		String name = catDetails.getName().toUpperCase();
		catDetails.setName(name);
		IssueCategoryDto dto = new ModelMapper().map(catDetails, IssueCategoryDto.class);
		IssueCategoryDto saveDto = categoryService.saveCategory(dto);		
		return new ModelMapper().map(saveDto, IssueCategoryResponseModel.class);
	}

	@PutMapping(path = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public IssueCategoryResponseModel updateCategory(@PathVariable String categoryId,@RequestBody IssueCategoryRequestModel catDetails) {
		IssueCategoryDto dto = new ModelMapper().map(catDetails, IssueCategoryDto.class);
		IssueCategoryDto updateDto = categoryService.updateCategory(categoryId,dto);	
		return new ModelMapper().map(updateDto, IssueCategoryResponseModel.class);
	}
}