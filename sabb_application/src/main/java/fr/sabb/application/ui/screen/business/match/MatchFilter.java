package fr.sabb.application.ui.screen.business.match;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;

public class MatchFilter extends VerticalLayout {
	 	private final Grid<Match> grid;
	    private final TextField nameFilter;
	    private final DateField dateField;

	    public MatchFilter(Grid<Match> grid) {
	    	this.grid = grid;
	        nameFilter = new TextField();
	        nameFilter.setPlaceholder("Equipe...");
	        nameFilter.addValueChangeListener(this::onNameFilterTextChange);
	        dateField = new DateField();
	        dateField.setPlaceholder("Journée du...");
	        dateField.addValueChangeListener(this::onDateFilterChange);
	        HorizontalLayout filter = new HorizontalLayout(nameFilter, dateField);
	        addComponent(filter);
	    }

	    private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
	        ListDataProvider<Match> dataProvider = (ListDataProvider<Match>) grid.getDataProvider();
	        dataProvider.setFilter(Match::getTeam, s -> caseInsensitiveContains(s, event.getValue()));
	    }
	    
	    private void onDateFilterChange(HasValue.ValueChangeEvent<LocalDate> event) {
	        ListDataProvider<Match> dataProvider = (ListDataProvider<Match>) grid.getDataProvider();
	        dataProvider.setFilter(Match::getMatchDate, s -> dateContains(s, event.getValue()));
	    }

	    private Boolean dateContains(Timestamp s, LocalDate localDate) {
	    	LocalDateTime convertedDate = s.toLocalDateTime();
			return localDate == null ? true : (convertedDate.getYear() == localDate.getYear() && convertedDate.getDayOfYear() == localDate.getDayOfYear());
		}

		private Boolean caseInsensitiveContains(Team where, String what) {
	        return where.getName().toLowerCase().contains(what.toLowerCase());
	    }
}
