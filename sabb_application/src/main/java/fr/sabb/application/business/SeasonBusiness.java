package fr.sabb.application.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.sabb.application.service.official.OfficialService;
import fr.sabb.application.service.transport.TransportService;

@Component
public class SeasonBusiness {

	@Autowired
	private TransportService transportService;
	
	@Autowired
	private OfficialService officialService;
	
	public void changePhase() {
		this.transportService.unvalidAllTransportForCurrentSeason();
		this.officialService.unvalidAllOfficialForCurrentSeason();
	}
}
