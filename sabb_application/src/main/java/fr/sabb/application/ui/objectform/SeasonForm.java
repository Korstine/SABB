package fr.sabb.application.ui.objectform;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import fr.sabb.application.data.object.Season;
import fr.sabb.application.ui.MainUI;

@SpringUI
public class SeasonForm extends FormLayout {
	
    private Season season;
    
    private Binder<Season> binder = new Binder<>(Season.class);
    
	private TextField name = new TextField("Saison");
	private CheckBox active = new CheckBox("Active");
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	
	private MainUI mainUI;
	
	public SeasonForm(MainUI mainUI) {
		this.mainUI = mainUI;
		
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
		mainUI.getMapper().delete(season);
		mainUI.updateList();
	}
	
	private void save() {
		mainUI.getMapper().insert(season);
		mainUI.updateList();
	}
}
