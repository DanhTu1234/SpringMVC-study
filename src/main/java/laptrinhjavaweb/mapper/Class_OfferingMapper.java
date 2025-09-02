package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.Class_OfferingModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Class_OfferingMapper implements RowMapper<Class_OfferingModel> {

    @Override
    public Class_OfferingModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Class_OfferingModel class_offeringModel = new Class_OfferingModel();

        // Đọc từ các cột đã được đặt tên đại diện (alias)
        class_offeringModel.setId(rs.getLong("id"));
        class_offeringModel.setOfferingCode(rs.getString("offering_code"));
        class_offeringModel.setCourseId(rs.getLong("course_id"));
        class_offeringModel.setSemesterId(rs.getLong("semester_id"));
        class_offeringModel.setTeacherId(rs.getLong("teacher_id"));
        class_offeringModel.setLms_Offering_Id(rs.getLong("lms_offering_id"));

        // Điền thông tin tên vào các đối tượng con
        class_offeringModel.getCourse_catalog().setTitle(rs.getString("title"));
        class_offeringModel.getSemester().setName(rs.getString("name"));
        class_offeringModel.getUser().setFullName(rs.getString("full_name"));

        return class_offeringModel;
    }
}
