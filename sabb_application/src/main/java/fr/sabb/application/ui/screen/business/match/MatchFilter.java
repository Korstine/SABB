package fr.sabb.application.ui.screen.business.match;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.ValidationException;
import fr.sabb.application.business.PlanningSheetGeneratorBusiness;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.CommonFilter;

public class MatchFilter extends CommonFilter {
	private final Grid<Match> grid;
	private final TextField nameFilter;
	private final DateField dateField;
	private LocalDate weeklySelected;

	public MatchFilter(Grid<Match> grid, PlanningSheetGeneratorBusiness planningSheetGeneratorBusiness) {
		this.grid = grid;
		nameFilter = new TextField();
		nameFilter.setPlaceholder("Equipe...");
		nameFilter.addValueChangeListener(this::onNameFilterTextChange);
		dateField = new DateField();
		dateField.setPlaceholder("Journée du...");
		dateField.addValueChangeListener(this::onDateFilterChange);
		Button generateSheet = new Button("Générer la feuille de match");
		generateSheet.addClickListener(e -> {
			try {
				if (this.weeklySelected != null) {
					planningSheetGeneratorBusiness.generateSABBWeeklySheet(weeklySelected);
					planningSheetGeneratorBusiness.generateCTCWeeklySheet(weeklySelected);
				} else {
					generateSheet.setComponentError(new UserError("Veuillez au préalable selectionner une date"));
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				generateSheet.setComponentError(new UserError(ex.getMessage()));
			}
		});
		HorizontalLayout filter = new HorizontalLayout(nameFilter, dateField, generateSheet);
		addComponent(filter);
	}

	private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		ListDataProvider<Match> dataProvider = (ListDataProvider<Match>) grid.getDataProvider();
		dataProvider.setFilter(Match::getTeam, s -> caseInsensitiveContains(s, event.getValue()));
	}

	private void onDateFilterChange(HasValue.ValueChangeEvent<LocalDate> event) {
		this.weeklySelected = event.getValue();
		ListDataProvider<Match> dataProvider = (ListDataProvider<Match>) grid.getDataProvider();
		dataProvider.setFilter(Match::getMatchDate, s -> dateContains(s, event.getValue()));
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

	private Boolean caseInsensitiveContains(Team where, String what) {
		return where.getName().toLowerCase().contains(what.toLowerCase());
	}
}
