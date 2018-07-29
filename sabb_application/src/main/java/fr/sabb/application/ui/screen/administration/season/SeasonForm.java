package fr.sabb.application.ui.screen.administration.season;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Season;
import fr.sabb.application.ui.screen.CommonForm;

public class SeasonForm extends CommonForm<Season> {
	
	private Binder<Season> binder = new Binder<>(Season.class);
    
	private TextField name = new TextField("Saison");
	private TextField referenceYear = new TextField("Année de référence");
	private CheckBox active = new CheckBox("Active");
	
	
	public SeasonForm(SeasonView view) {
		super(view);
		
		setSizeUndefined();
	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    addComponents(name, referenceYear, active, buttons);
	    binder.forField(referenceYear).withConverter(new StringToIntegerConverter("")).bind(Season::getReferenceYear,Season::setReferenceYear);
	    binder.bindInstanceFields(this);
	}
	

	@Override
	public Binder<Season> getBinder() {
		return binder;
	}


	@Override
	public TextField getDefaultSelectAll() {
		return name;
	}


	@Override
	public String getValidationExceptionMessage() {
		return "Il ne doit y avoir qu'une seule saison d'active.";
	}

}