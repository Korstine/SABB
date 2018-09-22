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
public interface TeamMapper extends SabbMapper<Team> {

	@Select("SELECT * FROM sabb.team ORDER BY id")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "name", column = "name"),
			@Result(property = "active", column = "active"),
			@Result(property = "season", column = "id_season", one = @One(select = "getSeason")),
			@Result(property = "association", column = "id_association", one = @One(select = "getAssociation")),
			@Result(property = "category", column = "id_category", one = @One(select = "getCategory")),
			@Result(property = "ffbbUniqueId", column = "ffbb_unique_id"),
			@Result(property = "sort", column = "sort"),
			@Result(property = "excelReference", column = "excel_reference"),
			@Result(property = "excelReferenceCtc", column = "excel_reference_ctc")})
	List<Team> getAll();

	@Insert("INSERT INTO sabb.team(name,id_season,id_category,id_association,active, ffbb_unique_id,sort, sex, ctc, excel_reference, excel_reference_ctc) VAlUES(#{name}, #{season.id},#{category.id},#{association.id}, #{active}, #{ffbbUniqueId}, #{sort}, #{sex}, #{ctc}, #{excelReference}, #{excelReferenceCtc})")
	void insert(Team team);

	@Delete("DELETE FROM sabb.team WHERE id=#{id}")
	void delete(Team team);

	@Update("UPDATE sabb.team SET name=#{name}, active=#{active}, id_season=#{season.id},id_category=#{category.id},id_association=#{association.id}, ffbb_unique_id=#{ffbbUniqueId}, sort=#{sort}, sex=#{sex}, ctc=#{ctc}, excel_reference=#{excelReference}, excel_reference_ctc=#{excelReferenceCtc} WHERE id=#{id}")
	void update(Team team);

}
