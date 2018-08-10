package fr.sabb.application.service.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.mapper.CategoryMapper;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.SubCategory;
import fr.sabb.application.service.SabbObjectServiceImpl;
import fr.sabb.application.service.subcategory.SubCategoryService;

@Service
public class CategoryServiceImpl extends SabbObjectServiceImpl<Category> implements CategoryService {

	@Autowired
	private CategoryMapper mapper;

	@Autowired
	private SubCategoryService subCategoryService;

	@Override
	public SabbMapper<Category> getMapper() {
		return mapper;
	}

	@Override
	public List<Category> getAllActive() {
		return getAll().stream().filter(Category::isActive).collect(Collectors.toList());
	}

	@Override
	public Category getCategoryBySubCategoryName(String subCategoryName, String sex) {
		return this.subCategoryService.getAll().stream()
				.filter(c -> c.getName().equals(subCategoryName) && c.getCategory().isActive())
				.filter(s -> this.filterBySex(s, sex))
				.map(SubCategory::getCategory).findFirst().orElse(null);
	}
	
	private boolean filterBySex(SubCategory subCategory, String sex) {
		if (subCategory.getSex() == null) {
			return true;
		}
		return subCategory.getSex().equals(sex);
	}

}
