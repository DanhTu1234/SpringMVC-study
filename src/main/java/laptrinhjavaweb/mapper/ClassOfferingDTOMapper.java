package laptrinhjavaweb.mapper;

import laptrinhjavaweb.dto.Class_OfferingDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassOfferingDTOMapper implements RowMapper<Class_OfferingDTO> {

    @Override
    public Class_OfferingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        Class_OfferingDTO  class_OfferingDTO = new Class_OfferingDTO();
        class_OfferingDTO.setLcmsOfferingId(rs.getLong("lcmsOfferingId"));
        class_OfferingDTO.setOffering_code(rs.getString("offering_code"));
        class_OfferingDTO.setCourse_title(rs.getString("course_title"));
        class_OfferingDTO.setSemester_name(rs.getString("semester_name"));
        class_OfferingDTO.setTeacher_name(rs.getString("teacher_name"));

        return class_OfferingDTO;
    }
}
