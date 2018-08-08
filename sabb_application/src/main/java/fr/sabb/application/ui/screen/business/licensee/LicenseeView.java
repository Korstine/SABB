package fr.sabb.application.ui.screen.business.licensee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Upload;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.licensee.LicenseeService;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class LicenseeView extends CommonView<Licensee>{
	@Autowired
	private LicenseeService licenseeService;
	
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
		return null;
	}

	@Override
	public Licensee createItem() {
		return null;
	}

	@Override
	public String getButtonName() {
		return null;
	}

	@Override
	public void setColumns(Grid<Licensee> grid) {
		grid.setColumns("numLicensee", "name", "firstname", "phone", "mail", "adress");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		HorizontalLayout formLayout = null;

			formLayout = new HorizontalLayout(new LicenseeFilter(grid));
		addComponent(formLayout);
		
this.licenseeService.fillDBWithCsvFile("C:/Users/flori/Downloads/M01_qualifies_complet_L_E_P_V (1).csv");
		super.enter(event);
	}

}
