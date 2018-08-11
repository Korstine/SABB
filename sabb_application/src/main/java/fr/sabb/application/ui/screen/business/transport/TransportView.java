package fr.sabb.application.ui.screen.business.transport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Transport;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.service.licensee.LicenseeService;
import fr.sabb.application.service.match.MatchService;
import fr.sabb.application.service.transport.TransportService;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.ui.screen.CommonView;

@Component
public class TransportView extends CommonView<Transport> {

	@Autowired
	private TransportService service;
	
	@Autowired
	private LicenseeService licenseeService;
	
	@Autowired
	private MatchService matchService;
	
	
	public static final String VIEW_NAME = "Gestion des Transports";
	
	@Override
	public Grid<Transport> getGrid() {
		return new Grid<>(Transport.class);
	}

	@Override
	public SabbObjectService<Transport> getService() {
		return service;
	}

	@Override
	public CommonForm<Transport> getForm() {
		return new TransportForm(this);
	}

	@Override
	public Transport createItem() {
		return new Transport();
	}

	@Override
	public String getButtonName() {
		return "Nouveau transport";
	}

	@Override
	public void setColumns(Grid<Transport> grid) {
		grid.setColumns("id", "match", "licensee1", "licensee2", "licensee3");
	}

	/**
	 * @return the licenseeService
	 */
	public LicenseeService getLicenseeService() {
		return licenseeService;
	}

	/**
	 * @return the matchService
	 */
	public MatchService getMatchService() {
		return matchService;
	}

}
