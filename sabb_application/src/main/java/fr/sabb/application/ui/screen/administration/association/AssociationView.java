package fr.sabb.application.ui.screen.administration.association;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.assocation.AssociationService;
import fr.sabb.application.service.licensee.LicenseeService;
import fr.sabb.application.ui.screen.CommonFilter;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class AssociationView  extends CommonView<Association> {

	@Autowired
	private AssociationService service;
	
	@Autowired
	private LicenseeService licenseeService;
	
	public AssociationView() {
		super();
	}

	public static final String VIEW_NAME = "Gestion des clubs";

	@Override
	public Grid<Association> getGrid() {
		return new Grid<>(Association.class);
	}

	@Override
	public SabbObjectService<Association> getService() {
		return service;
	}

	@Override
	public CommonForm<Association> getForm() {
		return new AssociationForm(this);
	}

	@Override
	public Association createItem() {
		return new Association();
	}

	@Override
	public String getButtonName() {
		return "Ajout d'un club";
	}

	@Override
	public void setColumns(Grid<Association> grid) {
		grid.setColumns("id", "name", "nameFfbb", "nameFfbbCtc", "ffbbLocation", "ffbbLocationBis", "main", "active");
	}

	@Override
	public CommonFilter getFilter() {
		return null;
	}

	/**
	 * @return the licenseeService
	 */
	public LicenseeService getLicenseeService() {
		return licenseeService;
	}


}
