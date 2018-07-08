package fr.sabb.application.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.data.mapper.SeasonMapper;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.service.season.SeasonService;
import fr.sabb.application.ui.objectform.SeasonForm;

@SpringUI
@Theme("valo")
public class MainUI  extends UI {

    @Autowired
    private SeasonService service;
    private Season season;

    private Grid<Season> gridSeasons = new Grid<>(Season.class);
    
    private SeasonForm form = new SeasonForm(this);

    @Override
    protected void init(VaadinRequest request) {
    	List seasons = service.getAll();
    	
    	gridSeasons.setColumns("id", "name", "active");
    	
    	Button addSeasonBtn = new Button("Add new season");
    	addSeasonBtn.addClickListener(e -> {
    		gridSeasons.asSingleSelect().clear();
    		form.setSeason(new Season());
    	});
    	
    	HorizontalLayout toolbar = new HorizontalLayout(addSeasonBtn);
    	
    	HorizontalLayout main = new HorizontalLayout(gridSeasons, form);
    	main.setSizeFull();
    	gridSeasons.setSizeFull();
    	main.setExpandRatio(gridSeasons, 1);

        VerticalLayout layout = new VerticalLayout(
        		toolbar, main);
        
        gridSeasons.setItems(seasons);
        
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        
        form.setVisible(false);
        
        gridSeasons.asSingleSelect().addValueChangeListener(e -> {
        	if(e.getValue() == null) {
        		form.setVisible(false);
        	} else {
        		form.setSeason(e.getValue());
        	}
        });
    }
    
    public void updateList() {
        List<Season> customers = service.getAll();
        gridSeasons.setItems(customers);
    }


    public SeasonService getService() {
    	return this.service;
    }

}
