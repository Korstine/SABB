package fr.sabb.application.ui.screen.business.licensee;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.CommonFilter;

public class LicenseeFilter extends CommonFilter<Licensee> {
	private final Grid<Licensee> grid;
	private final TextField nameFilter;
	private final TextField teamField;

	public LicenseeFilter(Grid<Licensee> grid) {
		this.grid = grid;
		nameFilter = new TextField();
		nameFilter.setPlaceholder("Nom...");
		nameFilter.addValueChangeListener(this::onNameFilterTextChange);
		teamField = new TextField();
		teamField.setPlaceholder("Equipe...");
		teamField.addValueChangeListener(this::onTeamFilterTextChange);
		HorizontalLayout filter = new HorizontalLayout(nameFilter, teamField);
		addComponent(filter);
	}

	private void onTeamFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		ListDataProvider<Licensee> dataProvider = (ListDataProvider<Licensee>) grid.getDataProvider();
		dataProvider.setFilter(Licensee::getTeam, s -> caseInsensitiveContains(s, event.getValue()));
	}

	private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		dataProvider = (ListDataProvider<Licensee>) grid.getDataProvider();
		dataProvider.setFilter(Licensee::getName, s -> caseInsensitiveContains(s, event.getValue()));
	}

	private Boolean caseInsensitiveContains(Team where, String what) {
		return where.getName().toLowerCase().contains(what.toLowerCase());
	}

	private Boolean caseInsensitiveContains(String where, String what) {
		return where.toLowerCase().contains(what.toLowerCase());
	}

}
