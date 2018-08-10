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

import fr.sabb.application.data.object.SubCategory;

@Mapper
public interface SubCategoryMapper extends SabbMapper<SubCategory> {

	@Select("SELECT * FROM sabb.sub_category ORDER BY id")
	@Results({ @Result(property = "category", column = "id_category", one = @One(select = "getCategory")) })
	List<SubCategory> getAll();

	@Insert("INSERT INTO sabb.sub_category(name,id_category,sex) VAlUES(#{name}, #{category.id}, #{sex})")
	void insert(SubCategory subCategory);

	@Delete("DELETE FROM sabb.sub_category WHERE id=#{id}")
	void delete(SubCategory subCategory);

	@Update("UPDATE sabb.sub_category SET name=#{name}, id_category=#{category.id}, sex=#{sex} WHERE id=#{id}")
	void update(SubCategory subCategory);
}
