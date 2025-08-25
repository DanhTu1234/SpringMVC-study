package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.CategoryCourseModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryCourseMapper implements RowMapper<CategoryCourseModel> {

    @Override
    public CategoryCourseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryCourseModel course = new CategoryCourseModel();
        course.setId(rs.getLong("id"));
        course.setName(rs.getString("name"));
        course.setCode(rs.getString("code"));
        return course;
    }
}
