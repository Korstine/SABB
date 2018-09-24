package fr.sabb.application.service.team;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.mapper.TeamMapper;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectServiceImpl;
import fr.sabb.application.service.season.SeasonService;

@Service
public class TeamServiceImpl extends SabbObjectServiceImpl<Team> implements TeamService {

	@Autowired
	TeamMapper mapper;

	@Autowired
	private SeasonService seasonService;

	@Override
	public SabbMapper<Team> getMapper() {
		return mapper;
	}

	@Override
	public void updateOrInsert(Team team) throws ValidationException {
		if (team.getCategory() == null || team.getAssociation() == null || team.getSeason() == null) {
			throw new ValidationException();
		}
		super.updateOrInsert(team);
	}

	@Override
	public List<Team> getAllActiveForCurrentSeason() {
		return this.getAll().stream().filter(Team::isActive)
				.filter(t -> t.getSeason().getId() == seasonService.getCurrentSeason().getId())
				.collect(Collectors.toList());
	}

	@Override
	public Team getFirstTeamForCategoryAndSex(int idCategory, String sex) {
		return this.getAllActiveForCurrentSeason().stream().filter(t -> t.getCategory().getId() == idCategory)
				.filter(t -> t.getSex().equals(sex)).sorted(Comparator.comparing(Team::getSort)).findFirst()
				.orElse(null);
	}

}
