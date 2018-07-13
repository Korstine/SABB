package fr.sabb.application.ui.screen.administration.team;

import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.ui.SabbUI;
import fr.sabb.application.ui.screen.administration.CommonForm;
import fr.sabb.application.ui.screen.administration.CommonView;

public class TeamView extends CommonView<Team> {

	public TeamView(SabbUI ui) {
		super(ui);
	}

	public static final String VIEW_NAME = "Gestion des equipes";

	@Override
	public Grid<Team> getGrid() {
		return new Grid<>(Team.class);
	}

	@Override
	public SabbObjectService<Team> getService() {
		return ui.getTeamService();
	}

	@Override
	public CommonForm<Team> getForm() {
		return new TeamForm(this);
	}

	@Override
	public Team createItem() {
		return new Team();
	}

	@Override
	public String getButtonName() {
		return "Ajout d'une equipe";
	}

	@Override
	public void setColumns(Grid<Team> grid) {
		grid.setColumns("id", "name", "category", "association", "active");
	}

}
