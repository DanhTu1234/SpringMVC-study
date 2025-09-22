package laptrinhjavaweb.mapper;

import laptrinhjavaweb.dto.CourseSectionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseSectionDTOMapper implements RowMapper<CourseSectionDTO> {
    @Override
    public CourseSectionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseSectionDTO  courseSectionDTO = new CourseSectionDTO();
        courseSectionDTO.setCourseid(rs.getLong("courseid"));
        courseSectionDTO.setSection(rs.getLong("section"));
        courseSectionDTO.setName(rs.getString("name"));

        return courseSectionDTO;
    }
}
