package fr.sabb.application.service.official;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Official;
import fr.sabb.application.service.SabbObjectService;

public interface OfficialService  extends SabbObjectService<Official>{

	Official getOfficialFromMatch(Match match);
	
	int countLicenseeOfficialNumber(Licensee licensee);
	
	void unvalidAllOfficialForCurrentSeason();
}
