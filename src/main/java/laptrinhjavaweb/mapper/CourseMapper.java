package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.CourseModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<CourseModel> {

    @Override
    public CourseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseModel course = new CourseModel();
        course.setId(rs.getLong("id"));
        course.setCategoryCourseId(rs.getLong("category_id"));
        course.getCategory().setName(rs.getString("name"));
        course.setFullName(rs.getString("fullname"));
        course.setShortName(rs.getString("shortname"));
        course.setSummary(rs.getString("summary"));
        course.setCreatedDate(rs.getTimestamp("created_date"));
        course.setModifiedDate(rs.getTimestamp("modified_date"));
        course.setCreatedBy(rs.getString("created_by"));
        course.setModifiedBy(rs.getString("modified_by"));
        course.setLmsCourseId(rs.getLong("lms_course_id"));
        return course;
    }
}
