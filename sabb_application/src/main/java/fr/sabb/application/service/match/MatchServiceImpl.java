package fr.sabb.application.service.match;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.mapper.MatchMapper;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectServiceImpl;

@Service
public class MatchServiceImpl extends SabbObjectServiceImpl<Match> implements MatchService {

	@Autowired
	private MatchMapper mapper;

	@Override
	public SabbMapper<Match> getMapper() {
		return mapper;
	}

	@Override
	public void updateOrInsert(Match match) throws ValidationException {
		Match existingMatch = this.getMatch(match);
		if (existingMatch != null) {
			match.setId(existingMatch.getId());
		}
		super.updateOrInsert(match);
	}

	@Override
	public Match getMatch(Match match) {
		return mapper.getMatch(match);
	}

	@Override
	public List<Match> getAllExtMatchByTeam(Team team) {
		return this.getAll().stream().filter(m -> m.getTeam().getId() == team.getId()).filter(m -> !m.isHome())
				.collect(Collectors.toList());
	}

	@Override
	public List<Match> getAllExt() {
		return this.getAll().stream().filter(m -> !m.isHome()).collect(Collectors.toList());
	}

	@Override
	public List<Match> getAllMatchByTeam(Team team) {
		return this.getAll().stream().filter(m -> m.getTeam().getId() == team.getId()).collect(Collectors.toList());
	}

	@Override
	public Stream<Match> getAllMatchForCurrentSeasonByWeekOfYear(int year, int weekOfYear) {
		return this.getAll().stream().filter(m -> isMatchPlaceDuringWeekend(m, year, weekOfYear));
	}

	@Override
	public Stream<Match> getAllCTCMatchForCurrentSeasonByWeekOfYear(int year, int weekOfYear) {
		return this.getAll().stream().filter(m -> isMatchPlaceDuringWeekend(m, year, weekOfYear)).filter(m -> m.getTeam().isCtc());
	}

	private boolean isMatchPlaceDuringWeekend(Match match, int year, int weekOfYear) {
		Calendar calMatch = Calendar.getInstance();
		calMatch.setTime(match.getMatchDate());
		return calMatch.get(Calendar.YEAR) == year && calMatch.get(Calendar.WEEK_OF_YEAR) == weekOfYear;
	}

	@Override
	public Match getExtRencontreByOpponent(String opponent, Team team) {
		return getAllExtMatchByTeam(team).stream().filter(m -> m.getOpponent().equals(opponent)).findFirst().orElse(null);
	}

	@Override
	public Stream<Match> getAllHomeMainMatchForCurrentSeason() {
		return this.getAll().stream().filter(m -> m.getTeam().getSeason().isActive()).filter(m -> m.isHome()).filter(this::playInMainAssociation);
	}
	
	private boolean playInMainAssociation(Match match) {
		return Boolean.logicalOr(match.getTeam().getAssociation().isMain() && !match.isLocationSwitched(),
				!match.getTeam().getAssociation().isMain() && match.isLocationSwitched());
	}
}
