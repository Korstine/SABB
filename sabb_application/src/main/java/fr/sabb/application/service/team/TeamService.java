package fr.sabb.application.service.team;

import java.util.List;

import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectService;

public interface TeamService extends SabbObjectService<Team>{

	List<Team> getAllActiveForCurrentSeason();

	Team getFirstTeamForCategoryAndSex(int idCategory, String sex);

}
