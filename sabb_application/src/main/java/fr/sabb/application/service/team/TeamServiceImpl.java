package fr.sabb.application.service.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.mapper.TeamMapper;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectServiceImpl;

@Service
public class TeamServiceImpl extends SabbObjectServiceImpl<Team>implements TeamService{
	
	@Autowired
	TeamMapper mapper;

	@Override
	public SabbMapper<Team> getMapper() {
		return mapper;
	}


}
