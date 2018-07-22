package fr.sabb.application.service.match;

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

}
