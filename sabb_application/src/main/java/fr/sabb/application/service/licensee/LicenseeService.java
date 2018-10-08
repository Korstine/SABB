package fr.sabb.application.service.licensee;

import java.util.List;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.OfficialLicensee;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectService;

public interface LicenseeService extends SabbObjectService<Licensee> {

	void fillDBWithCsvFile(Association association, String fileName);

	List<Licensee> getAllByTeam(Team team);

	List<OfficialLicensee> getAllOfficialLicenseeByTeam(Team team);

}
