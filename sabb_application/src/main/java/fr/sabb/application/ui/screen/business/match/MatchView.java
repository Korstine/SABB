package fr.sabb.application.ui.screen.business.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

import fr.sabb.application.data.object.Match;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.match.MatchService;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class MatchView extends CommonView<Match> {

	@Autowired
	private MatchService matchService;
	
	public static final String VIEW_NAME = "List des Rencontres";
	
	@Override
	public Grid<Match> getGrid() {
		return new Grid<>(Match.class);
	}

	@Override
	public SabbObjectService<Match> getService() {
		return matchService;
	}

	@Override
	public CommonForm<Match> getForm() {
		return null;
	}

	@Override
	public Match createItem() {
		return null;
	}

	@Override
	public String getButtonName() {
		return null;
	}

	@Override
	public void setColumns(Grid<Match> grid) {
		grid.setColumns("id", "opponent", "matchDate", "idFFBB", "home");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		HorizontalLayout formLayout = null;

			formLayout = new HorizontalLayout(new MatchFilter(grid));
		addComponent(formLayout);
	}

}
