package fr.sabb.application.service.licensee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.mapper.LicenseeMapper;
import fr.sabb.application.data.mapper.SabbMapper;
import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.service.SabbObjectServiceImpl;
import fr.sabb.application.service.category.CategoryService;
import fr.sabb.application.service.team.TeamService;

@Service
public class LicenseeServiceImpl extends SabbObjectServiceImpl<Licensee> implements LicenseeService {

	@Autowired
	private LicenseeMapper mapper;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TeamService teamService;

	@Override
	public SabbMapper<Licensee> getMapper() {
		return mapper;
	}

	@Override
	public void fillDBWithCsvFile(String fileName) {
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
			bufferReader.lines().filter(s -> !s.isEmpty() && !s.startsWith(";") && !s.contains("cd_org"))
					.forEach(this::readLicenseeLine);
		} catch (IOException ex) {
			System.out.println("problème lors du parsage");
		}
	}

	private void readLicenseeLine(String line) {
		Licensee licensee = new Licensee();
		String[] fields = line.split(";");
		licensee.setName(fields[3]);
		licensee.setFirstname(fields[4]);
		licensee.setAdress(String.format("%s;%s", fields[6], fields[5]));
		licensee.setSex(fields[10]);
		licensee.setNumLicensee(fields[15]);
		licensee.setPhone(getPhone(fields[18], fields[19], fields[20]));
		licensee.setMail(fields[21]);
		licensee.setCategory(this.getCategory(fields[12], licensee.getSex()));
		licensee.setTeam(getTeam(licensee.getCategory()));
		licensee.setDateOfBirth(getDateOfBirth(fields[14]));
		this.upsert(licensee);
	}

	private Team getTeam(Category category) {
		if (category != null && category.isAutobind()) {
			return teamService.getFirstTeamForCategory(category.getId());
		}
		return null;
	}

	private Timestamp getDateOfBirth(String dateStr) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("PB PARSE !" + e);
		}

		return new Timestamp(date.getTime());
	}

	private String getPhone(String phoneDom, String phoneWork, String phoneMobile) {
		if (!phoneMobile.isEmpty()) {
			return phoneMobile.replace(" ", "").replaceAll("\\.", "");
		}
		if (!phoneWork.isEmpty()) {
			return phoneWork.replace(" ", "").replaceAll("\\.", "");
		}
		if (!phoneDom.isEmpty()) {
			return phoneDom.replace(" ", "").replaceAll("\\.", "");
		}
		return null;
	}

	private Category getCategory(String categoryStr, String sex) {
		return this.categoryService.getCategoryBySubCategoryName(categoryStr, sex);
	}

	private void upsert(Licensee licensee) {
		Licensee oldLicensee = mapper.getLicenseeByNum(licensee.getNumLicensee());
		if (oldLicensee != null) {
			licensee.setId(oldLicensee.getId());
		}
		try {
			updateOrInsert(licensee);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Licensee> getAllByTeam(Team team) {
		return this.getAll().stream().filter(l-> l.getTeam() != null).filter(l -> l.getTeam().getId() == team.getId()).collect(Collectors.toList());
	}
}
