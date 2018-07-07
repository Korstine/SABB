package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import fr.sabb.application.data.object.Season;

@Mapper
public interface SeasonMapper {

	@Select("SELECT * FROM sabb.season ORDER BY id")
	List<Season> getAll();
	
	@Insert("INSERT INTO sabb.season(name,active) VAlUES(#{name}, #{active})")
	void insert(Season season);
	
	@Delete("DELETE FROM sabb.season WHERE id=#{id}")
	void delete(Season season);

}
