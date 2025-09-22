package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.CourseSectionModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseSectionMapper implements RowMapper<CourseSectionModel> {

    @Override
    public CourseSectionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseSectionModel  courseSectionModel = new CourseSectionModel();
        courseSectionModel.setId(rs.getLong("id"));
        courseSectionModel.setCourseId(rs.getLong("course_id"));
        courseSectionModel.setParent(rs.getLong("parent"));
        courseSectionModel.setName(rs.getString("name"));
        courseSectionModel.setLmsSectionId(rs.getLong("lms_section_id"));
        return courseSectionModel;
    }
}
