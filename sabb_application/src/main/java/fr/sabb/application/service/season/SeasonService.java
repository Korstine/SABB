package fr.sabb.application.service.season;

import fr.sabb.application.data.object.Season;
import fr.sabb.application.service.SabbObjectService;

public interface SeasonService extends SabbObjectService<Season>{

	Season getCurrentSeason();
}
