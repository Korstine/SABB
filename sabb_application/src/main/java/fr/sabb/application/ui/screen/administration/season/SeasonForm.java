package fr.sabb.application.ui.screen.administration.season;

import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Season;
import fr.sabb.application.ui.screen.administration.CommonForm;

public class SeasonForm extends CommonForm<Season> {
	
	private Binder<Season> binder = new Binder<>(Season.class);
    
	private TextField name = new TextField("Saison");
	private CheckBox active = new CheckBox("Active");
	
	
	public SeasonForm(SeasonView view) {
		super(view);
		
		setSizeUndefined();
	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    addComponents(name, active, buttons);
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

}