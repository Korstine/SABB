package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.data.object.Team;

@Mapper
public interface MatchMapper extends SabbMapper {

	@Select("SELECT * FROM sabb.match ORDER BY id")
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="opponent", column="opponent"),
			@Result(property="matchDate", column="match_date"),
			@Result(property="team", column="id_team", one=@One(select="getTeam")),
			@Result(property="idFFBB", column="id_ffbb"),
			@Result(property="home", column="home"),
	})
	List<Match> getAll();
	
	@Select("SELECT * FROM sabb.match WHERE id_ffbb=#{idFFBB} AND id_team=#{team.id}")
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="opponent", column="opponent"),
			@Result(property="matchDate", column="match_date"),
			@Result(property="team", column="id_team", one=@One(select="getTeam")),
			@Result(property="idFFBB", column="id_ffbb"),
			@Result(property="home", column="home"),
	})
	Match getMatch(Match match);
	
	@Select("SELECT * FROM sabb.team WHERE id=#{idTeam}")
	Team getTeam(int idTeam);
	
	@Insert("INSERT INTO sabb.match(opponent,id_team,match_date,id_ffbb,home) VAlUES(#{opponent}, #{team.id}, #{matchDate}, #{idFFBB}, #{home})")
	void insert(Match match);
	
	@Update("UPDATE sabb.match SET opponent=#{opponent}, id_team=#{team.id}, match_date=#{matchDate}, home=#{home} WHERE id=#{id}")
	void update(Match match);
}
