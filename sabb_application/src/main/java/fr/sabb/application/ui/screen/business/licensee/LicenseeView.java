package fr.sabb.application.ui.screen.business.licensee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.api.client.util.Objects;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Upload;

import fr.sabb.application.business.EmergementListingAGBusiness;
import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.assocation.AssociationService;
import fr.sabb.application.service.licensee.LicenseeService;
import fr.sabb.application.service.season.SeasonService;
import fr.sabb.application.service.team.TeamService;
import fr.sabb.application.ui.screen.CommonFilter;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;
import fr.sabb.application.ui.screen.business.official.OfficialFilter;

@Component
public class LicenseeView extends CommonView<Licensee>{
	@Autowired
	private LicenseeService licenseeService;
	
	@Autowired
	@Lazy
	private TeamService teamService;
	
	@Autowired
	@Lazy
	private AssociationService associationService;
	
	@Autowired
	@Lazy
	private SeasonService seasonService;
	
	@Autowired
	private EmergementListingAGBusiness emergementListingAGBusiness;
	
	public static final String VIEW_NAME = "List des Licencies";
	@Override
	public Grid<Licensee> getGrid() {
		return new Grid<>(Licensee.class);
	}

	@Override
	public SabbObjectService<Licensee> getService() {
		return licenseeService;
	}

	@Override
	public CommonForm<Licensee> getForm() {
		return new LicenseeForm(this);
	}

	@Override
	public Licensee createItem() {
		return new Licensee();
	}

	@Override
	public String getButtonName() {
		return "Ajout d'un licencié";
	}

	@Override
	public void setColumns(Grid<Licensee> grid) {
		grid.setColumns("numLicensee", "name", "firstname", "team", "phone", "mail", "adress", "association");
	}
	
	/**
	 * @return the teamService
	 */
	public TeamService getTeamService() {
		return teamService;
	}

	/**
	 * @return the licenseeService
	 */
	public LicenseeService getLicenseeService() {
		return licenseeService;
	}

	/**
	 * @return the associationService
	 */
	public AssociationService getAssociationService() {
		return associationService;
	}

	@Override
	public CommonFilter getFilter() {
		if (filter == null) {
			filter = new LicenseeFilter(grid,emergementListingAGBusiness);
		}
		return filter;
	}
	
	@Override
	public List<Licensee> getItems() {
		return getService().getAll().stream().filter(l -> Objects.equal(seasonService.getCurrentSeason(), l.getSeason())).collect(Collectors.toList());
	}

}
