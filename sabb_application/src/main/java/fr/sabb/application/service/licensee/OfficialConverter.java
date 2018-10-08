package fr.sabb.application.service.licensee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.OfficialLicensee;
import fr.sabb.application.service.official.OfficialService;

@Service
public class OfficialConverter {
	
	@Autowired
	private OfficialService officialService;

	protected OfficialLicensee convertLicensee (Licensee licensee) {
		return new OfficialLicensee(licensee, this.officialService.countLicenseeOfficialNumber(licensee));
	}
}
