package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.sabb.application.data.object.Category;

@Mapper
public interface CategoryMapper extends SabbMapper<Category> {

	@Select("SELECT * FROM sabb.category ORDER BY id")
	List<Category> getAll();
	
	@Insert("INSERT INTO sabb.category(name,active,autobind) VAlUES(#{name}, #{active}, #{autobind})")
	void insert(Category category);
	
	@Delete("DELETE FROM sabb.category WHERE id=#{id}")
	void delete(Category category);
	
	@Update("UPDATE sabb.category SET name=#{name}, active=#{active}, autobind=#{autobind} WHERE id=#{id}")
	void update(Category category);
}
