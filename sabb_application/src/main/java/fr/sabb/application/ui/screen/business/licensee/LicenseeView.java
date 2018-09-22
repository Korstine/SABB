package fr.sabb.application.ui.screen.business.licensee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Upload;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.licensee.LicenseeService;
import fr.sabb.application.service.team.TeamService;
import fr.sabb.application.ui.screen.CommonFilter;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class LicenseeView extends CommonView<Licensee>{
	@Autowired
	private LicenseeService licenseeService;
	
	@Autowired
	@Lazy
	private TeamService teamService;
	
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
		return null;
	}

	@Override
	public void setColumns(Grid<Licensee> grid) {
		grid.setColumns("numLicensee", "name", "firstname", "team", "phone", "mail", "adress");
	}
	
	/**
	 * @return the teamService
	 */
	public TeamService getTeamService() {
		return teamService;
	}

	@Override
	public CommonFilter getFilter() {
		return new LicenseeFilter(grid);
	}

}
