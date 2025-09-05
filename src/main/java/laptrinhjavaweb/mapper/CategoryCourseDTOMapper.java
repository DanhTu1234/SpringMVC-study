package laptrinhjavaweb.mapper;

import laptrinhjavaweb.dto.CourseCategoryDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryCourseDTOMapper implements RowMapper<CourseCategoryDTO> {

    @Override
    public CourseCategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseCategoryDTO courseCategoryDTO = new CourseCategoryDTO();
        courseCategoryDTO.setName(rs.getString("name"));
        courseCategoryDTO.setDescription(rs.getString("description"));
        courseCategoryDTO.setParent(rs.getLong(("parent")));

        return courseCategoryDTO;
    }
}
