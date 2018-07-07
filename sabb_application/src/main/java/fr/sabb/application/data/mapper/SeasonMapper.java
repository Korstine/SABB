package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import fr.sabb.application.data.object.Season;

@Mapper
public interface SeasonMapper {

	@Select("SELECT * FROM sabb.season ORDER BY id")
	List<Season> getAll();

}
