package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.sabb.application.data.object.Season;

@Mapper
public interface SeasonMapper extends SabbMapper<Season> {

	@Select("SELECT * FROM sabb.season ORDER BY id")
	List<Season> getAll();
	
	@Insert("INSERT INTO sabb.season(name,active,reference_year) VAlUES(#{name}, #{active}, #{referenceYear})")
	void insert(Season season);
	
	@Delete("DELETE FROM sabb.season WHERE id=#{id}")
	void delete(Season season);
	
	@Update("UPDATE sabb.season SET name=#{name}, active=#{active}, reference_year=#{referenceYear} WHERE id=#{id}")
	void update(Season season);
}
