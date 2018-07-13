package fr.sabb.application.service.category;

import java.util.List;

import fr.sabb.application.data.object.Category;
import fr.sabb.application.service.SabbObjectService;

public interface CategoryService extends SabbObjectService<Category>{

	List<Category> getAllActive();
}
