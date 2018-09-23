package fr.sabb.application.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import fr.sabb.application.ui.screen.MainScreen;
import fr.sabb.application.ui.screen.administration.association.AssociationView;
import fr.sabb.application.ui.screen.administration.category.CategoryView;
import fr.sabb.application.ui.screen.administration.season.SeasonView;
import fr.sabb.application.ui.screen.administration.subcategory.SubCategoryView;
import fr.sabb.application.ui.screen.administration.team.TeamView;
import fr.sabb.application.ui.screen.business.licensee.LicenseeView;
import fr.sabb.application.ui.screen.business.match.MatchView;
import fr.sabb.application.ui.screen.business.official.OfficialView;
import fr.sabb.application.ui.screen.business.transport.TransportView;

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
	@Autowired
	private LicenseeView licenseeView;
	@Autowired
	private SubCategoryView subCategoryView;
	@Autowired
	private TransportView transportView;
	@Autowired
	private OfficialView officialView;

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

	/**
	 * @return the licenseeView
	 */
	public LicenseeView getLicenseeView() {
		return licenseeView;
	}

	/**
	 * @return the subCategoryView
	 */
	public SubCategoryView getSubCategoryView() {
		return subCategoryView;
	}

	/**
	 * @return the transportView
	 */
	public TransportView getTransportView() {
		return transportView;
	}

	/**
	 * @return the officialView
	 */
	public OfficialView getOfficialView() {
		return officialView;
	}

}
