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
			@Result(property = "teamReferee", column = "id_team_referee", one = @One(select = "getTeam"))
			})
	List<Official> getAll();

	@Insert("INSERT INTO sabb.official(id_match,id_team_table,id_team_referee) VAlUES(#{match.id}, #{teamTable.id},#{teamReferee.id})")
	void insert(Official official);

	@Delete("DELETE FROM sabb.official WHERE id=#{id}")
	void delete(Official official);

	@Update("UPDATE sabb.official SET id_match=#{match.id}, id_team_table=#{teamTable.id}, id_team_referee=#{teamReferee.id} WHERE id=#{id}")
	void update(Official official);

}
