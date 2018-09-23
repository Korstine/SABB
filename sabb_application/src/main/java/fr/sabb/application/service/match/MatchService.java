package fr.sabb.application.service.match;

import java.util.List;
import java.util.stream.Stream;


import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectService;

public interface MatchService  extends SabbObjectService<Match>{

	
	Match getMatch(Match match);
	
	List<Match> getAllExt();
	
	List<Match> getAllMatchByTeam(Team team);
	
	List<Match> getAllExtMatchByTeam(Team team);

	Stream<Match> getAllMatchForCurrentSeasonByWeekOfYear(int year, int weekOfYear);

	Stream<Match> getAllCTCMatchForCurrentSeasonByWeekOfYear(int year, int weekOfYear);
	
	Stream<Match> getAllHomeMainMatchForCurrentSeason();

	Match getExtRencontreByOpponent(String opponent, Team team);
}
