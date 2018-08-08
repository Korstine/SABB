package fr.sabb.application.ui.screen.administration.subcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.SubCategory;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.category.CategoryService;
import fr.sabb.application.service.subcategory.SubCategoryService;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class SubCategoryView extends CommonView<SubCategory> {
	
	@Autowired
	private SubCategoryService service;
	@Autowired
	@Lazy
	private CategoryService categoryService;

	public static final String VIEW_NAME = "Gestion des sous-categories";

	@Override
	public Grid<SubCategory> getGrid() {
		return  new Grid(SubCategory.class);
	}

	@Override
	public SabbObjectService<SubCategory> getService() {
		return service;
	}

	@Override
	public CommonForm<SubCategory> getForm() {
		return new SubCategoryForm(this);
	}

	@Override
	public SubCategory createItem() {
		return new SubCategory();
	}

	@Override
	public String getButtonName() {
		return "Ajouter une sous categorie";
	}

	@Override
	public void setColumns(Grid<SubCategory> grid) {
		grid.setColumns("id", "name", "category");
	}

	/**
	 * @return the categoryService
	 */
	public CategoryService getCategoryService() {
		return categoryService;
	}

}
