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

import fr.sabb.application.data.object.Association;
import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.data.object.Team;

@Mapper
public interface TeamMapper extends SabbMapper {
	
	@Select("SELECT * FROM sabb.team ORDER BY id")
	@Results(value= {
			@Result(property="id", column="id"),
			@Result(property="name", column="name"),
			@Result(property="active", column="active"),
			@Result(property="season", column="id_season", one=@One(select="getSeason")),
			@Result(property="association", column="id_association", one=@One(select="getAssociation")),
			@Result(property="category", column="id_category", one=@One(select="getCategory")),
			@Result(property="ffbbUniqueId", column="ffbb_unique_id")
	})
	List<Team> getAll();
	
	@Insert("INSERT INTO sabb.team(name,id_season,id_category,id_association,active, ffbb_unique_id) VAlUES(#{name}, #{season.id},#{category.id},#{association.id}, #{active}, #{ffbbUniqueId})")
	void insert(Team team);
	
	@Delete("DELETE FROM sabb.team WHERE id=#{id}")
	void delete(Team team);
	
	@Update("UPDATE sabb.team SET name=#{name}, active=#{active}, id_season=#{season.id},id_category=#{category.id},id_association=#{association.id}, ffbb_unique_id=#{ffbbUniqueId} WHERE id=#{id}")
	void update(Team team);
	
	@Select("SELECT * FROM sabb.season WHERE id=#{idSeason}")
	Season getSeason(int idSeason);
	
	@Select("SELECT * FROM sabb.association WHERE id=#{idAssociation}")
	@Results(value= {
			@Result(property="nameFfbb", column="name_ffbb"),	
		})
	Association getAssociation(int idAssociation);
	
	@Select("SELECT * FROM sabb.category WHERE id=#{idCategory}")
	Category getCategory(int idCategory);
	
}
