package fr.sabb.application.ui.screen.administration.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;

import fr.sabb.application.ValidationException;
import fr.sabb.application.business.MatchFillerBusiness;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.assocation.AssociationService;
import fr.sabb.application.service.category.CategoryService;
import fr.sabb.application.service.season.SeasonService;
import fr.sabb.application.service.team.TeamService;
import fr.sabb.application.ui.screen.CommonFilter;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class TeamView extends CommonView<Team> {
	
	@Autowired
	private TeamService service;
	
	@Autowired
	@Lazy
	private CategoryService categoryService;
	@Autowired
	@Lazy
	private AssociationService associationService;
	@Autowired
	@Lazy
	private SeasonService seasonService;
	
	@Autowired
	private MatchFillerBusiness matchFillerBusiness;

	public TeamView() {
		super();
	}

	public static final String VIEW_NAME = "Gestion des equipes";

	@Override
	public Grid<Team> getGrid() {
		return new Grid<>(Team.class);
	}

	@Override
	public SabbObjectService<Team> getService() {
		return service;
	}

	@Override
	public CommonForm<Team> getForm() {
		return new TeamForm(this);
	}

	@Override
	public Team createItem() {
		Team team = new Team();
		team.setSeason(seasonService.getCurrentSeason());
		return team;
	}

	@Override
	public String getButtonName() {
		return "Ajout d'une equipe";
	}

	@Override
	public void setColumns(Grid<Team> grid) {
		grid.setColumns("id", "name", "category", "sex", "sort","association", "active", "ffbbUniqueId", "ctc", "excelReference", "excelReferenceCtc", "refereeReplacmentLabel","hasOfficialReferee");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		
		Button reloadMatchs = new Button("Recharger tous les matchs");
		reloadMatchs.addClickListener(e -> {
			try {
				matchFillerBusiness.reloadGameFromFFBBForAllTeam();
			} catch (ValidationException ex) {
				reloadMatchs.setComponentError(new UserError(ex.getMessage()));
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		addComponent(reloadMatchs);
	}

	/**
	 * @return the categoryService
	 */
	public CategoryService getCategoryService() {
		return categoryService;
	}

	/**
	 * @return the associationService
	 */
	public AssociationService getAssociationService() {
		return associationService;
	}

	/**
	 * @return the seasonService
	 */
	public SeasonService getSeasonService() {
		return seasonService;
	}

	/**
	 * @return the matchFillerBusiness
	 */
	public MatchFillerBusiness getMatchFillerBusiness() {
		return matchFillerBusiness;
	}


	@Override
	public CommonFilter getFilter() {
		return null;
	}

}
