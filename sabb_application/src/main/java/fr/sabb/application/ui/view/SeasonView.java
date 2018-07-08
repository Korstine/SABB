package fr.sabb.application.ui.view;

import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.data.object.Season;
import fr.sabb.application.service.season.SeasonService;
import fr.sabb.application.ui.SabbUI;
import fr.sabb.application.ui.objectform.SeasonForm;

@SpringView
public class SeasonView  extends CssLayout implements View {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2729510873919363830L;

	public static final String VIEW_NAME = "Gestion des saisons";

    private Grid<Season> gridSeasons = new Grid<>(Season.class);
    
    private SeasonForm form;
    private SabbUI ui;

    public SeasonView(SabbUI ui) {
    	this.ui = ui;
        setSizeFull();

        form = new SeasonForm(this);

        List<Season> seasons = getService().getAll();
    	
    	gridSeasons.setColumns("id", "name", "active");
    	
    	Button addSeasonBtn = new Button("Add new season");
    	addSeasonBtn.addClickListener(e -> {
    		gridSeasons.asSingleSelect().clear();
    		form.setSeason(new Season());
    	});
    	
    	
    	HorizontalLayout main = new HorizontalLayout(gridSeasons);
    	main.setSizeFull();
    	gridSeasons.setSizeFull();
    	main.setExpandRatio(gridSeasons, 1);

        gridSeasons.setItems(seasons);
        
        form.setVisible(false);
        
        gridSeasons.asSingleSelect().addValueChangeListener(e -> {
        	if(e.getValue() == null) {
        		form.setVisible(false);
        	} else {
        		form.setSeason(e.getValue());
        	}
        });
        
        HorizontalLayout formLayout = new HorizontalLayout(addSeasonBtn, form);
        VerticalLayout layout = new VerticalLayout(
        		main, formLayout);
        layout.setMargin(true);
        layout.setSpacing(true);
        addComponent(layout);

    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
    }
    
    public void updateList() {
	      List<Season> customers = getService().getAll();
	      gridSeasons.setItems(customers);
    }

	/**
	 * @return the service
	 */
	public SeasonService getService() {
		return ui.getSeasonService();
	}
    
}
