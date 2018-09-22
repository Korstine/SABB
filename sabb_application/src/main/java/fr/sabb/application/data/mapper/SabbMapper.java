package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.SabbObject;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.data.object.Team;

public interface SabbMapper<T extends SabbObject> {

	List<T> getAll();

	void insert(T object);

	void delete(T object);

	void update(T object);
	
	@Select("SELECT * FROM sabb.team WHERE id=#{idTeam}")
	@Results(value = {@Result(property = "season", column = "id_season", one = @One(select = "getSeason")),
			@Result(property = "association", column = "id_association", one = @One(select = "getAssociation")),
			@Result(property = "category", column = "id_category", one = @One(select = "getCategory")),
			@Result(property = "ffbbUniqueId", column = "ffbb_unique_id"),
			@Result(property = "excelReference", column = "excel_reference"),
			@Result(property = "excelReferenceCtc", column = "excel_reference_ctc")})
	Team getTeam(int idTeam);
	
	@Select("SELECT * FROM sabb.season WHERE id=#{idSeason}")
	Season getSeason(int idSeason);
	
	@Select("SELECT * FROM sabb.category WHERE id=#{idCategory}")
	Category getCategory(int idCategory);
	
	@Select("SELECT * FROM sabb.match WHERE id=#{idMatch}")
	@Results(value = {@Result(property = "team", column = "id_team", one = @One(select = "getTeam"))})
	Match getMatchById(int idMatch);
	
	@Select("SELECT * FROM sabb.licensee WHERE id=#{idLicensee}")
	Licensee getLicensee(int idLicensee);
	
	@Select("SELECT * FROM sabb.association WHERE id=#{idAssociation}")
	@Results(value = { @Result(property = "nameFfbb", column = "name_ffbb"), })
	Association getAssociation(int idAssociation);
}
