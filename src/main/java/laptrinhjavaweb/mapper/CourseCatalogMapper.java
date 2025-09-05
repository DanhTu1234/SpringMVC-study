package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.Course_CatalogModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseCatalogMapper implements RowMapper<Course_CatalogModel> {
    @Override
    public Course_CatalogModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course_CatalogModel course_catalog = new Course_CatalogModel();
        course_catalog.setId(rs.getLong("id"));
        course_catalog.setCourseCode(rs.getString("course_code"));
        course_catalog.setTitle(rs.getString("title"));
        course_catalog.setCredit(rs.getString("credit"));
        course_catalog.setMajorId(rs.getLong("major_id"));
        return course_catalog;
    }
}
