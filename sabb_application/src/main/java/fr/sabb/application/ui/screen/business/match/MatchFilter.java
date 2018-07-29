package fr.sabb.application.ui.screen.business.match;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;

public class MatchFilter extends VerticalLayout {
	 	private final Grid<Match> grid;
	    private final TextField nameFilter;

	    public MatchFilter(Grid<Match> grid) {
	    	this.grid = grid;
	        nameFilter = new TextField();
	        nameFilter.setPlaceholder("Name...");
	        nameFilter.addValueChangeListener(this::onNameFilterTextChange);
	        addComponent(nameFilter);

	    }

	    private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
	        ListDataProvider<Match> dataProvider = (ListDataProvider<Match>) grid.getDataProvider();
	        dataProvider.setFilter(Match::getTeam, s -> caseInsensitiveContains(s, event.getValue()));
	    }

	    private Boolean caseInsensitiveContains(Team where, String what) {
	        return where.getName().toLowerCase().contains(what.toLowerCase());
	    }
}
