package sys.app.its.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sys.app.its.dto.IssueCategoryDto;
import sys.app.its.entity.IssueCategoryEntity;
import sys.app.its.exception.ApplicationServiceException;
import sys.app.its.exception.ErrorMessages;
import sys.app.its.repository.IssueCategoryRepository;
import sys.app.its.service.IssueCategoryService;
import sys.app.its.utility.Utility;

@AllArgsConstructor
@Service
public class IssueCategoryServiceImplementation implements IssueCategoryService {
	private IssueCategoryRepository categoryRepository;
	private Utility utils;

	@Override
	public List<IssueCategoryDto> allCategory() {
		List<IssueCategoryDto> returnList = new ArrayList<IssueCategoryDto>();
		List<IssueCategoryEntity> entityList = categoryRepository.findAll();
		ModelMapper mapper = new ModelMapper();
		for (IssueCategoryEntity entity : entityList) {
			returnList.add(mapper.map(entity, IssueCategoryDto.class));
		}
		return returnList;
	}

	@Override
	public IssueCategoryDto saveCategory(IssueCategoryDto dto) {		
		if(categoryRepository.findByName(dto.getName().toUpperCase()) != null) throw new  ApplicationServiceException(ErrorMessages.CATEGORY_NAME_ALREADY_EXISTS.getErrorMessage());
		IssueCategoryEntity entity = new ModelMapper().map(dto, IssueCategoryEntity.class);
		entity.setCategoryId(utils.generateCategoryId(10));
		IssueCategoryEntity saveEntity = categoryRepository.save(entity);	
		return new ModelMapper().map(saveEntity, IssueCategoryDto.class);
	}

	@Override
	public IssueCategoryDto updateCategory(String categoryId,IssueCategoryDto dto) {
		if(categoryRepository.findByName(dto.getName().toUpperCase()) != null) throw new  ApplicationServiceException(ErrorMessages.CATEGORY_NAME_ALREADY_EXISTS.getErrorMessage());
		IssueCategoryEntity entity = categoryRepository.findByCategoryId(categoryId);
		entity.setName(dto.getName().toString().toUpperCase());
		IssueCategoryEntity updateEntity = categoryRepository.save(entity);	
		return new ModelMapper().map(updateEntity, IssueCategoryDto.class);
	}

	

}
