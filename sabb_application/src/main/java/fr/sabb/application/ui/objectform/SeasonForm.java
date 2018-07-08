package fr.sabb.application.ui.objectform;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.UserError;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.ui.SabbUI;
import fr.sabb.application.ui.view.SeasonView;

@SpringUI
public class SeasonForm extends FormLayout {
	
    private Season season;
    
    private Binder<Season> binder = new Binder<>(Season.class);
    
	private TextField name = new TextField("Saison");
	private CheckBox active = new CheckBox("Active");
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	
	private SeasonView view;
	
	public SeasonForm(SeasonView view) {
		this.view = view;
		
		setSizeUndefined();
	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    addComponents(name, active, buttons);
	    
	    save.setStyleName(ValoTheme.BUTTON_PRIMARY);
	    save.setClickShortcut(KeyCode.ENTER);
	    
	    binder.bindInstanceFields(this);
	    
	    save.addClickListener(e -> this.save());
	    delete.addClickListener(e -> this.delete());
	    
	}
	
	public void setSeason(Season season) {
		this.season = season;
		binder.setBean(season);
		setVisible(true);
		name.selectAll();
	}

	private void delete() {
view.getService().delete(season);
view.updateList();
	}
	
	private void save() {
		try {
			view.getService().updateOrInsert(season);
		} catch (ValidationException ex) {
			save.setComponentError(new UserError("Il ne doit y avoir qu'une seule saison d'active."));
		}
		view.updateList();
	}
}
