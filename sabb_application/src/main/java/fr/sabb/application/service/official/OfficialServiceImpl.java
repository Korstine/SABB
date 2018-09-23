package fr.sabb.application.service.official;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.mapper.OfficialMapper;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Official;
import fr.sabb.application.service.SabbObjectServiceImpl;

@Service
public class OfficialServiceImpl extends SabbObjectServiceImpl<Official> implements OfficialService {

	@Autowired
	private OfficialMapper mapper;

	@Override
	public SabbMapper<Official> getMapper() {
		return mapper;
	}

	@Override
	public Official getOfficialFromMatch(Match match) {
		return mapper.getAll().stream().filter(o -> o.getMatch().getId() == match.getId()).findFirst()
				.orElse(new Official(match));
	}

}
