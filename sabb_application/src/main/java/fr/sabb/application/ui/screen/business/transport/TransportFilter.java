package fr.sabb.application.ui.screen.business.transport;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Transport;
import fr.sabb.application.ui.screen.CommonFilter;

public class TransportFilter extends CommonFilter {
	private final Grid<Transport> grid;
	private final TextField nameFilter;

	public TransportFilter(Grid<Transport> grid) {
		this.grid = grid;
		nameFilter = new TextField();
		nameFilter.setPlaceholder("Equipe...");
		nameFilter.addValueChangeListener(this::onNameFilterTextChange);
		HorizontalLayout filter = new HorizontalLayout(nameFilter);
		addComponent(filter);
	}

	private void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
		ListDataProvider<Transport> dataProvider = (ListDataProvider<Transport>) grid.getDataProvider();
		dataProvider.setFilter(Transport::getMatch, s -> caseInsensitiveContains(s, event.getValue()));
	}

	private Boolean caseInsensitiveContains(Match where, String what) {
		return where.getTeam().getName().toLowerCase().contains(what.toLowerCase());
	}

}
