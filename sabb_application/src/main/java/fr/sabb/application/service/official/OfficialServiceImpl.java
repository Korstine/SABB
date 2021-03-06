package fr.sabb.application.service.official;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.mapper.OfficialMapper;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Official;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.service.SabbObjectServiceImpl;
import fr.sabb.application.service.season.SeasonService;
import fr.sabb.application.utils.DateUtils;

@Service
public class OfficialServiceImpl extends SabbObjectServiceImpl<Official> implements OfficialService {

	@Autowired
	private OfficialMapper mapper;

	@Autowired
	private SeasonService seasonService;

	@Override
	public SabbMapper<Official> getMapper() {
		return mapper;
	}

	@Override
	public List<Official> getAll() {
		return getMapper().getAll().stream().filter(o -> o.getMatch() != null).collect(Collectors.toList());
	}

	@Override
	public Official getOfficialFromMatch(Match match) {
		return this.getAll().stream().filter(o -> o.getMatch().getId() == match.getId()).findFirst()
				.orElse(new Official(match));
	}

	@Override
	public int countLicenseeOfficialNumber(Licensee licensee) {
		return getAllWithCaching().stream().filter(o -> isLicenseeOfficie(o, licensee)).collect(Collectors.toList())
				.size();
	}

	private boolean isLicenseeOfficie(Official official, Licensee licensee) {
		return isSameLicensee(official.getLicenseeReferee1(), licensee)
				|| isSameLicensee(official.getLicenseeReferee2(), licensee)
				|| isSameLicensee(official.getLicenseeTable1(), licensee)
				|| isSameLicensee(official.getLicenseeTable2(), licensee);
	}

	private boolean isSameLicensee(Licensee officialLicensee, Licensee licensee) {
		return officialLicensee != null && officialLicensee.getId() == licensee.getId();
	}

	@Override
	public void unvalidAllOfficialForCurrentSeason() {
		Season currentSeason = this.seasonService.getCurrentSeason();
		this.getAll().stream().filter(o -> o.getMatch().getTeam().getSeason().equals(currentSeason))
				.forEach(this::unvalidOfficial);
	}

	@Override
	public void updateOrInsert(Official official) throws ValidationException {
		Official existingOfficial = this.getOfficialFromMatch(official.getMatch());
		if (existingOfficial != null) {
			official.setId(existingOfficial.getId());
		}
		super.updateOrInsert(official);
	}

	private void unvalidOfficial(Official official) {
		official.setMatch(null);
		this.mapper.update(official);
	}

	@Override
	public int countScoreOfficial(Licensee licensee) {
		
		List<Match> tmp = getAllWithCaching().stream()
				.filter(o -> isLicenseeOfficie(o, licensee))
				.map(Official::getMatch).collect(Collectors.toList());
		Optional<Match> tmp2 = tmp.stream()
				.sorted(Comparator.comparing(Match::getMatchDate).reversed())
				.findFirst();
		return tmp2
				.map(Match::getMatchDate)
				.map(DateUtils::calculateScore)
				.orElse(0);
	}
	
	
}
