package fr.sabb.application.ui.screen.administration.category;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Category;
import fr.sabb.application.service.category.CategoryService;
import fr.sabb.application.ui.SabbUI;
import fr.sabb.application.ui.screen.administration.CommonForm;
import fr.sabb.application.ui.screen.administration.CommonView;

public class CategoryView extends CommonView<Category> {

	public static final String VIEW_NAME = "Gestion des categories";


    public CategoryView(SabbUI ui) {
    	super(ui);
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
		return ui.getCategoryService();
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
		grid.setColumns("id", "name", "active");
	}

}
