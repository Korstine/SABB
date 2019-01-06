package fr.sabb.application.service.transport;

import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Transport;
import fr.sabb.application.service.SabbObjectService;

public interface TransportService extends SabbObjectService<Transport>{

	Transport getTransportOrBarByMatch(Match match);
	
	void unvalidAllTransportForCurrentSeason();
}
