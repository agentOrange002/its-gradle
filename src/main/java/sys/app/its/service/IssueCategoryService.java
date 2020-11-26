package sys.app.its.service;

import java.util.List;
import sys.app.its.dto.IssueCategoryDto;

public interface IssueCategoryService {
	List<IssueCategoryDto> allCategory();
	IssueCategoryDto saveCategory(IssueCategoryDto dto);
	IssueCategoryDto updateCategory(String categoryId,IssueCategoryDto dto);
}
