package fr.sabb.application.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import fr.sabb.application.service.assocation.AssociationService;
import fr.sabb.application.service.category.CategoryService;
import fr.sabb.application.service.season.SeasonService;
import fr.sabb.application.service.team.TeamService;
import fr.sabb.application.ui.screen.MainScreen;
import fr.sabb.application.ui.screen.administration.association.AssociationView;
import fr.sabb.application.ui.screen.administration.category.CategoryView;
import fr.sabb.application.ui.screen.administration.season.SeasonView;
import fr.sabb.application.ui.screen.administration.team.TeamView;
import fr.sabb.application.ui.screen.business.match.MatchView;

@SpringUI
@Theme("valo")
public class SabbUI extends UI {

	@Autowired
	private SeasonView seasonView;
	@Autowired
	private AssociationView associationView;
	@Autowired
	private TeamView teamView;
	@Autowired
	private CategoryView categoryView;
	@Autowired
	private MatchView matchView;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle("SabbApp");
		showMainView();
	}

	protected void showMainView() {
		addStyleName(ValoTheme.UI_WITH_MENU);
		setContent(new MainScreen(SabbUI.this));
		getNavigator().navigateTo(getNavigator().getState());
	}

	public static SabbUI get() {
		return (SabbUI) UI.getCurrent();
	}

	/**
	 * @return the seasonView
	 */
	public SeasonView getSeasonView() {
		return seasonView;
	}

	/**
	 * @return the associationView
	 */
	public AssociationView getAssociationView() {
		return associationView;
	}

	/**
	 * @return the teamView
	 */
	public TeamView getTeamView() {
		return teamView;
	}

	/**
	 * @return the categoryView
	 */
	public CategoryView getCategoryView() {
		return categoryView;
	}

	/**
	 * @return the matchView
	 */
	public MatchView getMatchView() {
		return matchView;
	}

}
