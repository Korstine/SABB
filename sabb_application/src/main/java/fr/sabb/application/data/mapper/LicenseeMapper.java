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

@Mapper
public interface LicenseeMapper extends SabbMapper<Licensee> {

	@Select("SELECT * FROM sabb.licensee ORDER BY id")
	@Results({ @Result(property = "team", column = "id_team", one = @One(select = "getTeam")),
			@Result(property = "category", column = "id_category", one = @One(select = "getCategory")) })
	List<Licensee> getAll();
	
	@Select("SELECT * FROM sabb.licensee where num_licensee=#{numLicensee}")
	@Results({ @Result(property = "team", column = "id_team", one = @One(select = "getTeam")),
			@Result(property = "category", column = "id_category", one = @One(select = "getCategory")) })
	Licensee getLicenseeByNum(String numLicensee);

	@Insert("INSERT INTO sabb.licensee(id_team, id_category, num_licensee, name, firstname, phone, mail, adress, date_of_birth, sex) VAlUES(#{team.id}, #{category.id}, #{numLicensee},  #{name}, #{firstname}, #{phone}, #{mail}, #{adress}, #{dateOfBirth}, #{sex})")
	void insert(Licensee licensee);

	@Update("UPDATE sabb.licensee SET id_category=#{category.id}, id_team=#{team.id}, num_licensee=#{numLicensee}, name=#{name}, firstname=#{firstname}, phone=#{phone}, mail=#{mail}, adress=#{adress}, date_of_birth=#{dateOfBirth}, sex=#{sex} WHERE id=#{id}")
	void update(Licensee licensee);
}
