package fr.sabb.application.service.transport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.mapper.TransportMapper;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Transport;
import fr.sabb.application.service.SabbObjectServiceImpl;
import fr.sabb.application.service.match.MatchService;

@Service
public class TransportServiceImpl extends SabbObjectServiceImpl<Transport>implements TransportService {

	@Autowired
	private TransportMapper mapper;
	
	@Autowired
	private MatchService matchService;
	
	@Override
	public SabbMapper<Transport> getMapper() {
		return mapper;
	}

	@Override
	public Transport getTransportOrBarByMatch(Match match) {
		if (match.isHome()) {
			Match extMatch = matchService.getExtRencontreByOpponent(match.getOpponent(), match.getTeam());
			if (extMatch != null) {
				return this.getAll().stream().filter(t -> t.getMatch().getId() == extMatch.getId()).findFirst().orElse(null);	
			}
			return null;
		} else {
			return this.getAll().stream().filter(t -> t.getMatch().getId() == match.getId()).findFirst().orElse(null);
		}
		
	}


}
