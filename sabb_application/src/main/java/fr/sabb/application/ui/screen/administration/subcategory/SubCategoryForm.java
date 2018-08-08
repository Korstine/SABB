package fr.sabb.application.ui.screen.administration.subcategory;

import com.vaadin.data.Binder;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.SubCategory;
import fr.sabb.application.ui.screen.CommonForm;

public class SubCategoryForm  extends CommonForm<SubCategory>{
	private Binder<SubCategory> binder = new Binder<>(SubCategory.class);

	private TextField name = new TextField("Sous Category");
	private ListSelect<Category> selectCategory = new ListSelect<>("Category");

	public SubCategoryForm(SubCategoryView view) {
		super(view);
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete);

		selectCategory.setItems(view.getCategoryService().getAllActive());
		selectCategory.setRows(5);
		selectCategory.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setCategory(e.getFirstSelectedItem().get());
			}
		});

		HorizontalLayout selectors = new HorizontalLayout(selectCategory);
		addComponents(name, selectors, buttons);
		binder.bindInstanceFields(this);
	}

	@Override
	public Binder<SubCategory> getBinder() {
		return binder;
	}

	@Override
	public TextField getDefaultSelectAll() {
		return name;
	}

	@Override
	public String getValidationExceptionMessage() {
		return "Tous les champs sont obligatoires";
	}
}
