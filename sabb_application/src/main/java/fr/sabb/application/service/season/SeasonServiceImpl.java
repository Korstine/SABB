package fr.sabb.application.service.season;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.mapper.SeasonMapper;
import fr.sabb.application.data.object.Season;

@Service
public class SeasonServiceImpl implements SeasonService
{

	@Autowired
    private SeasonMapper mapper;

	@Override
	public List<Season> getAll() {
		return mapper.getAll();
	}

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
	public void delete(Season season) {
		mapper.delete(season);
	}

	@Override
	public Optional<Season> getById(int id) {
		return this.getAll().stream().filter(s-> s.getId() == id).findFirst();
	}

	@Override
	public Season getCurrentSeason() {
		return this.getAll().stream().filter(Season::isActive).findFirst().orElse(null);
	}
}
