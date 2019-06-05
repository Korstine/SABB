package fr.sabb.application.ui.screen.business.licensee;

import java.time.LocalDate;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.business.EmergementListingAGBusiness;
import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.CommonFilter;

public class LicenseeFilter extends CommonFilter<Licensee> {
	private final Grid<Licensee> grid;
	private final TextField nameFilter;
	private final TextField teamField;
	private final DateField dateField;
	private LocalDate dateAG;

	public LicenseeFilter(Grid<Licensee> grid, EmergementListingAGBusiness emergementListingAGBusiness) {
		this.grid = grid;
		nameFilter = new TextField();
		nameFilter.setPlaceholder("Nom...");
		nameFilter.addValueChangeListener(this::onNameFilterTextChange);
		teamField = new TextField();
		teamField.setPlaceholder("Equipe...");
		teamField.addValueChangeListener(this::onTeamFilterTextChange);
		
		dateField = new DateField();
		dateField.setPlaceholder("Date AG");
		dateField.addValueChangeListener(this::onDateFilterChange);

		Button buttonAGListing = new Button("Générer le listing d'émargement AG");
		buttonAGListing.addClickListener(e ->{
			try {
				if (this.dateAG != null) {
					emergementListingAGBusiness.generateEmergementListing(dateAG);
				} else {
					buttonAGListing.setComponentError(new UserError("Veuillez au préalable selectionner une date"));
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				buttonAGListing.setComponentError(new UserError(ex.getMessage()));
			}
		});
		HorizontalLayout filter = new HorizontalLayout(nameFilter, teamField, dateField, buttonAGListing);
		addComponent(filter);
	}
	
	private void onDateFilterChange(HasValue.ValueChangeEvent<LocalDate> event) {
		this.dateAG = event.getValue();
	}

	
	private void onTeamFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		dataProvider = (ListDataProvider<Licensee>) grid.getDataProvider();
		dataProvider.setFilter(Licensee::getTeam, s -> caseInsensitiveContains(s, event.getValue()));
	}

	private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		dataProvider = (ListDataProvider<Licensee>) grid.getDataProvider();
		dataProvider.setFilter(Licensee::getName, s -> caseInsensitiveContains(s, event.getValue()));
	}

	private Boolean caseInsensitiveContains(Team where, String what) {
		return where != null && where.getName().toLowerCase().contains(what.toLowerCase());
	}

	private Boolean caseInsensitiveContains(String where, String what) {
		return where.toLowerCase().contains(what.toLowerCase());
	}

}
