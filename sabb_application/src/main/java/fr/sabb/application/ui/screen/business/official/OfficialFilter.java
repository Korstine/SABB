package fr.sabb.application.ui.screen.business.official;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.selection.MultiSelectionEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;

import fr.sabb.application.data.object.Official;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.team.TeamService;
import fr.sabb.application.ui.screen.CommonFilter;

public class OfficialFilter extends CommonFilter<Official> {
	private final Grid<Official> grid;
	private final DateField dateField;
	private LocalDate weeklySelected;
	
	private ListSelect<Team> selectTeam = new ListSelect<>("Team");

	public OfficialFilter(Grid<Official> grid, TeamService teamService) {
		this.grid = grid;
		dateField = new DateField();
		dateField.setPlaceholder("Journée du...");
		dateField.addValueChangeListener(this::onDateFilterChange);
		
		selectTeam.setItems(teamService.getAllActiveForCurrentSeason());
		selectTeam.setRows(5);
		selectTeam.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				onTeamFilterChange(e);
			}
		});
		
		HorizontalLayout filter = new HorizontalLayout(dateField, selectTeam);
		addComponent(filter);
	}

	private void onDateFilterChange(HasValue.ValueChangeEvent<LocalDate> event) {
		this.weeklySelected = event.getValue();
		dataProvider = (ListDataProvider<Official>) grid.getDataProvider();
		dataProvider.setFilter(o -> o.getMatch().getMatchDate(), s -> dateContains(s, event.getValue()));
	}
	
	private void onTeamFilterChange(MultiSelectionEvent<Team> e) {
		Team team = e.getFirstSelectedItem().get();
		dataProvider = (ListDataProvider<Official>) grid.getDataProvider();
		dataProvider.setFilter(o -> o, o -> (o.getTeamTable() != null && o.getTeamTable().getId() == team.getId()) || (o.getTeamReferee() != null && o.getTeamReferee().getId() == team.getId()));
	}

	private Boolean dateContains(Timestamp s, LocalDate localDate) {
		Date date = Date.valueOf(localDate);
		Calendar calFiltre = Calendar.getInstance();
		calFiltre.setTime(date);
		Calendar calMatch = Calendar.getInstance();
		calMatch.setTime(s);
		return localDate == null ? true
				: (calFiltre.get(Calendar.YEAR) == calMatch.get(Calendar.YEAR)
						&& calFiltre.get(Calendar.WEEK_OF_YEAR) == calMatch.get(Calendar.WEEK_OF_YEAR));
	}

}
