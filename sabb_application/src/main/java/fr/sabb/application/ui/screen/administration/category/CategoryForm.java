package fr.sabb.application.ui.screen.administration.category;

import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Category;
import fr.sabb.application.ui.screen.administration.CommonForm;

public class CategoryForm extends CommonForm<Category> {
	
    
    private Binder<Category> binder = new Binder<>(Category.class);
    
	private TextField name = new TextField("Categorie");
	private CheckBox active = new CheckBox("Active");
	
	
	public CategoryForm(CategoryView view) {
		super(view);
		
		setSizeUndefined();
	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    addComponents(name, active, buttons);
	    
	    binder.bindInstanceFields(this);
	}
	

	@Override
	public TextField getDefaultSelectAll() {
		return name;
	}


	@Override
	public Binder<Category> getBinder() {
		return binder;
	}
}
