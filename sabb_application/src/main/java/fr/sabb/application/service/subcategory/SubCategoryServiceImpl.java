package fr.sabb.application.service.subcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.mapper.SubCategoryMapper;
import fr.sabb.application.data.object.SubCategory;
import fr.sabb.application.service.SabbObjectServiceImpl;

@Service
public class SubCategoryServiceImpl extends SabbObjectServiceImpl<SubCategory> implements SubCategoryService {

	@Autowired
	private SubCategoryMapper mapper;

	@Override
	public SabbMapper<SubCategory> getMapper() {
		return mapper;
	}

}
