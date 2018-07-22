package fr.sabb.application.service.match;

import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectService;

public interface MatchService  extends SabbObjectService<Match>{

	
	Match getMatch(Match match);
}
