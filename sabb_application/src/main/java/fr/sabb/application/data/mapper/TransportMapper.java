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

import fr.sabb.application.data.object.Transport;

@Mapper
public interface TransportMapper  extends SabbMapper<Transport> {

	@Select("SELECT * FROM sabb.transport ORDER BY id")
	@Results(value = { 
			@Result(property = "match", column = "id_match", one = @One(select = "getMatchById")),
			@Result(property = "licensee1", column = "id_licensee_1", one = @One(select = "getLicensee")),
			@Result(property = "licensee2", column = "id_licensee_2", one = @One(select = "getLicensee")),
			@Result(property = "licensee3", column = "id_licensee_3", one = @One(select = "getLicensee"))
			})
	List<Transport> getAll();

	@Insert("INSERT INTO sabb.transport(id_match,id_licensee_1,id_licensee_2,id_licensee_3) VAlUES(#{match.id}, #{licensee1.id},#{licensee2.id},#{licensee3.id})")
	void insert(Transport transport);

	@Delete("DELETE FROM sabb.transport WHERE id=#{id}")
	void delete(Transport transport);

	@Update("UPDATE sabb.transport SET id_match=#{match.id}, id_licensee_1=#{licensee1.id}, id_licensee_2=#{licensee2.id},id_licensee_3=#{licensee3.id} WHERE id=#{id}")
	void update(Transport transport);

}
