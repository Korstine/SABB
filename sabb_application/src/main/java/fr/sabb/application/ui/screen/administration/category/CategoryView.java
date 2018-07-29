package fr.sabb.application.ui.screen.administration.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Category;
import fr.sabb.application.service.category.CategoryService;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class CategoryView extends CommonView<Category> {

	@Autowired
	private CategoryService service;

	public static final String VIEW_NAME = "Gestion des categories";

	public CategoryView() {
		super();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}

	/**
	 * @return the service
	 */
	@Override
	public CategoryService getService() {
		return service;
	}

	@Override
	public Grid<Category> getGrid() {
		return new Grid(Category.class);
	}

	@Override
	public CommonForm<Category> getForm() {
		return new CategoryForm(this);
	}

	@Override
	public Category createItem() {
		return new Category();
	}

	@Override
	public String getButtonName() {
		return "Add new category";
	}

	@Override
	public void setColumns(Grid<Category> grid) {
		grid.setColumns("id", "name", "autobind", "active");
	}

}
