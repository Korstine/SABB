package fr.sabb.application.ui.screen.administration.association;

import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

public class AssociationForm extends CommonForm<Association> {

private Binder<Association> binder = new Binder<>(Association.class);
    
	private TextField name = new TextField("Club");
	private CheckBox active = new CheckBox("Active");
	private CheckBox main = new CheckBox("Club principal");
	private TextField nameFfbb = new TextField("Nommage FFBB");
	private TextField nameFfbbCtc = new TextField("Nommage FFBB CTC");
	
	public AssociationForm(CommonView<Association> view) {
		super(view);
		setSizeUndefined();
	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    addComponents(name, nameFfbb, nameFfbbCtc, main, active, buttons);
	    
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

	@Override
	public String getValidationExceptionMessage() {
		return null;
	}

	@Override
	public void clearAllFormFields() {
		// TODO Auto-generated method stub
		
	}

}
