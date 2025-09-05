package laptrinhjavaweb.mapper;

import laptrinhjavaweb.dto.CourseDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDTOMapper implements RowMapper<CourseDTO> {

    @Override
    public CourseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseDTO  courseDTO = new CourseDTO();
        courseDTO.setFullname(rs.getString("fullname"));
        courseDTO.setShortname(rs.getString("shortname"));
        courseDTO.setSummary(rs.getString("summary"));
        courseDTO.setCategoryid(rs.getLong("categoryid"));

        return courseDTO;
    }
}
