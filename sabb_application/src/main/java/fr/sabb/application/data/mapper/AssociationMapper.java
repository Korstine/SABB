package fr.sabb.application.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import fr.sabb.application.data.object.Association;

@Mapper
public interface AssociationMapper extends SabbMapper{

	@Select("SELECT * FROM sabb.association ORDER BY id")
	@Results(value= {
		@Result(property="nameFfbb", column="name_ffbb"),	
	})
	List<Association> getAll();
	
	@Insert("INSERT INTO sabb.association(name,main,active,name_ffbb) VAlUES(#{name},#{main}, #{active}, #{nameFfbb})")
	void insert(Association association);
	
	@Delete("DELETE FROM sabb.association WHERE id=#{id}")
	void delete(Association association);
	
	@Update("UPDATE sabb.association SET name=#{name}, main=#{main}, active=#{active}, name_ffbb=#{nameFfbb} WHERE id=#{id}")
	void update(Association association);
}
