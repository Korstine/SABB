package fr.sabb.application.service.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.mapper.SeasonMapper;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.service.SabbObjectServiceImpl;

@Service
public class SeasonServiceImpl extends SabbObjectServiceImpl<Season> implements SeasonService
{

	@Autowired
    private SeasonMapper mapper;


	@Override
	public void updateOrInsert(Season season) throws ValidationException {
		if (season.isActive() && this.getCurrentSeason() != null && this.getCurrentSeason().getId() != season.getId()) {
			throw new ValidationException();
		}
		if (season.isPersisted()) {
			mapper.update(season);
		}else {
			mapper.insert(season);
		}
	}


	@Override
	public Season getCurrentSeason() {
		return this.getAll().stream().filter(Season::isActive).findFirst().orElse(null);
	}


	@Override
	public SabbMapper<Season> getMapper() {
		return mapper;
	}
}
