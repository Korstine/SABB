package fr.sabb.application.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.mapper.CategoryMapper;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.Category;
import fr.sabb.application.service.SabbObjectServiceImpl;

@Service
public class CategoryServiceImpl extends SabbObjectServiceImpl<Category> implements CategoryService{

	@Autowired
    private CategoryMapper mapper;
	

	@Override
	public SabbMapper<Category> getMapper() {
		return mapper;
	}

}
