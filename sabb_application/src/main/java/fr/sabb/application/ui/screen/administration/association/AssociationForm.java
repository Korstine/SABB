package fr.sabb.application.ui.screen.administration.association;

import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.ui.screen.administration.CommonForm;
import fr.sabb.application.ui.screen.administration.CommonView;

public class AssociationForm extends CommonForm<Association> {

private Binder<Association> binder = new Binder<>(Association.class);
    
	private TextField name = new TextField("Club");
	private CheckBox active = new CheckBox("Active");
	private CheckBox main = new CheckBox("Club principal");
	
	public AssociationForm(CommonView<Association> view) {
		super(view);
		setSizeUndefined();
	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    addComponents(name, main, active, buttons);
	    
	    binder.bindInstanceFields(this);
	}

	@Override
	public Binder<Association> getBinder() {
		return binder;
	}

	@Override
	public TextField getDefaultSelectAll() {
		return name;
	}

}
