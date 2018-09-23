package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.sabb.application.data.object.Official;

@Mapper
public interface OfficialMapper  extends SabbMapper<Official> {

	@Select("SELECT * FROM sabb.official ORDER BY id")
	@Results(value = { 
			@Result(property = "match", column = "id_match", one = @One(select = "getMatchById")),
			@Result(property = "teamTable", column = "id_team_table", one = @One(select = "getTeam")),
			@Result(property = "teamReferee", column = "id_team_referee", one = @One(select = "getTeam")),
			@Result(property = "licenseeTable1", column = "id_licensee_table_1", one = @One(select = "getLicensee")),
			@Result(property = "licenseeTable2", column = "id_licensee_table_2", one = @One(select = "getLicensee")),
			@Result(property = "licenseeReferee1", column = "id_licensee_referee_1", one = @One(select = "getLicensee")),
			@Result(property = "licenseeReferee2", column = "id_licensee_referee_2", one = @One(select = "getLicensee")),
			})
	List<Official> getAll();

	@Insert("INSERT INTO sabb.official(id_match,id_team_table,id_team_referee,id_licensee_table_1,id_licensee_table_2,id_licensee_referee_1,id_licensee_referee_2) VAlUES(#{match.id}, #{teamTable.id},#{teamReferee.id},#{licenseeTable1.id},#{licenseeTable2.id},#{licenseeReferee1.id},#{licenseeReferee2.id})")
	void insert(Official official);

	@Delete("DELETE FROM sabb.official WHERE id=#{id}")
	void delete(Official official);

	@Update("UPDATE sabb.official SET id_match=#{match.id}, id_team_table=#{teamTable.id}, id_team_referee=#{teamReferee.id}, id_licensee_table_1=#{licenseeTable1.id}, id_licensee_table_2=#{licenseeTable2.id}, id_licensee_referee_1=#{licenseeReferee1.id}, id_licensee_referee_2=#{licenseeReferee2.id}  WHERE id=#{id}")
	void update(Official official);

}
