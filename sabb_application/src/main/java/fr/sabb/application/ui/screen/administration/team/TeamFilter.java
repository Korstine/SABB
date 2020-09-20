package fr.sabb.application.ui.screen.administration.team;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Season;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.CommonFilter;

public class TeamFilter extends CommonFilter<Team> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6440772019740217412L;
	private final Grid<Team> grid;
	private final TextField seasonFilter;

	
	public TeamFilter(Grid<Team> grid) {
		this.grid = grid;
		seasonFilter = new TextField();
		seasonFilter.setPlaceholder("Season...");
		seasonFilter.addValueChangeListener(this::onNameFilterTextChange);
		HorizontalLayout filter = new HorizontalLayout(seasonFilter);
		addComponent(filter);
	}

	private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		dataProvider = (ListDataProvider<Team>) grid.getDataProvider();
		dataProvider.setFilter(Team::getSeason, s -> caseInsensitiveContains(s, event.getValue()));
	}


	private Boolean caseInsensitiveContains(Season where, String what) {
		return where.getName().toLowerCase().contains(what.toLowerCase());
	}
}
