package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.SabbObject;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.data.object.Team;

public interface SabbMapper<T extends SabbObject> {

	List<T> getAll();

	void insert(T object);

	void delete(T object);

	void update(T object);
	
	@Select("SELECT * FROM sabb.team WHERE id=#{idTeam}")
	Team getTeam(int idTeam);
	
	@Select("SELECT * FROM sabb.season WHERE id=#{idSeason}")
	Season getSeason(int idSeason);
	
	@Select("SELECT * FROM sabb.category WHERE id=#{idCategory}")
	Category getCategory(int idCategory);
}
