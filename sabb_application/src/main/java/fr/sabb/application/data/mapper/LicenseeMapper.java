package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Season;

@Mapper
public interface LicenseeMapper extends SabbMapper<Licensee> {

	@Select("SELECT * FROM sabb.licensee ORDER BY id")
	@Results({ @Result(property = "team", column = "id_team", one = @One(select = "getTeam")),
			@Result(property = "category", column = "id_category", one = @One(select = "getCategory")),
			@Result(property = "association", column = "id_association", one = @One(select = "getAssociation")),
			@Result(property="dateOfBirth", column="date_of_birth"),
			@Result(property = "numLicensee", column = "num_licensee"),
			@Result(property = "season", column = "id_season", one = @One(select = "getSeason")),})
	List<Licensee> getAll();
	
	@Select("SELECT * FROM sabb.licensee WHERE num_licensee=#{numLicensee} AND id_season = (SELECT id FROM sabb.season WHERE season.active)")
	@Results({ @Result(property = "team", column = "id_team", one = @One(select = "getTeam")),
			@Result(property = "category", column = "id_category", one = @One(select = "getCategory")) })
	Licensee getLicenseeByNum(String numLicensee);

	@Insert("INSERT INTO sabb.licensee(id_team, id_category, num_licensee, name, firstname, phone, mail, adress, date_of_birth, sex, id_association, id_season) VAlUES(#{team.id}, #{category.id}, #{numLicensee},  #{name}, #{firstname}, #{phone}, #{mail}, #{adress}, #{dateOfBirth}, #{sex}, #{association.id}, #{season.id})")
	void insert(Licensee licensee);

	@Update("UPDATE sabb.licensee SET id_category=#{category.id}, id_team=#{team.id}, num_licensee=#{numLicensee}, name=#{name}, firstname=#{firstname}, phone=#{phone}, mail=#{mail}, adress=#{adress}, sex=#{sex}, id_association=#{association.id}, id_season=#{season.id} WHERE id=#{id}")
	void update(Licensee licensee);
}
