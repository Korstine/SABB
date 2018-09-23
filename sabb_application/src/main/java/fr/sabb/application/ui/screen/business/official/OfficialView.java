package fr.sabb.application.ui.screen.business.official;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Official;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.match.MatchService;
import fr.sabb.application.service.official.OfficialService;
import fr.sabb.application.service.team.TeamService;
import fr.sabb.application.ui.screen.CommonFilter;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class OfficialView extends CommonView<Official> {

	@Autowired
	private OfficialService officialService;
	@Autowired
	@Lazy
	private TeamService teamService;
	@Autowired
	@Lazy
	private MatchService matchService;
	
	public static final String VIEW_NAME = "List des Tables et Arbitrages";

	@Override
	public Grid<Official> getGrid() {
		return new Grid<>(Official.class);
	}

	@Override
	public SabbObjectService<Official> getService() {
		return officialService;
	}

	@Override
	public CommonForm<Official> getForm() {
		return new OfficialForm(this);
	}

	@Override
	public Official createItem() {
		return new Official();
	}

	@Override
	public String getButtonName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColumns(Grid<Official> grid) {
		grid.setColumns("match", "match.matchDate", "teamTable", "teamReferee");
	}

	@Override
	public CommonFilter getFilter() {
		if (filter == null) {
			filter = new OfficialFilter(grid);
		}
		return filter;
	}

	/**
	 * @return the teamService
	 */
	public TeamService getTeamService() {
		return teamService;
	}
	
	@Override
	public List<Official> getItems() {
		return matchService.getAllHomeMainMatchForCurrentSeason().sorted((m1, m2) -> m1.getMatchDate().compareTo(m2.getMatchDate())).map(officialService::getOfficialFromMatch).collect(Collectors.toList());
	}
}
