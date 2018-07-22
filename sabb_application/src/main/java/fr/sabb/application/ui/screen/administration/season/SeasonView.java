package fr.sabb.application.ui.screen.administration.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Season;
import fr.sabb.application.service.season.SeasonService;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class SeasonView extends CommonView<Season> {

	@Autowired
	SeasonService service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2729510873919363830L;

	public static final String VIEW_NAME = "Gestion des saisons";

	public SeasonView() {
		super();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
	}

	/**
	 * @return the service
	 */
	@Override
	public SeasonService getService() {
		return service;
	}

	@Override
	public Grid<Season> getGrid() {
		return new Grid<>(Season.class);
	}

	@Override
	public CommonForm<Season> getForm() {
		return new SeasonForm(this);
	}

	@Override
	public Season createItem() {
		return new Season();
	}

	@Override
	public String getButtonName() {
		return "Add new season";
	}

	@Override
	public void setColumns(Grid<Season> grid) {
		grid.setColumns("id", "name", "active");

	}

}
