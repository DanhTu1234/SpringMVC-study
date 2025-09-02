package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.IClass_OfferingDAO;
import laptrinhjavaweb.dto.Class_OfferingDTO;
import laptrinhjavaweb.mapper.ClassOfferingDTOMapper;
import laptrinhjavaweb.mapper.Class_OfferingMapper;
import laptrinhjavaweb.mapper.CourseMapper;
import laptrinhjavaweb.model.Class_OfferingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

@Repository
public class Class_OfferingDAO implements IClass_OfferingDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Class_OfferingModel> findAll() {
        String sql = "SELECT class_offering.*, course_catalog.title, semester.name, user.full_name" +
                " FROM class_offering join course_catalog on class_offering.course_id = course_catalog.id " +
                "join semester on class_offering.semester_id = semester.id " +
                "join user on class_offering.teacher_id = user.id;";

        return jdbcTemplate.query(sql, new Class_OfferingMapper());
    }

    @Override
    public Class_OfferingModel insert(Class_OfferingModel class_OfferingModel) {
        String sql = "INSERT INTO class_offering (offering_code, course_id, semester_id, teacher_id) VALUES(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, class_OfferingModel.getOfferingCode());
                ps.setLong(2, class_OfferingModel.getCourseId());
                ps.setLong(3, class_OfferingModel.getSemesterId());
                ps.setLong(4, class_OfferingModel.getTeacherId());
                return ps;
            }
        }, keyHolder);

        // Lấy ID vừa được tạo ra và gán ngược lại vào đối tượng
        if (keyHolder.getKey() != null) {
            class_OfferingModel.setId(keyHolder.getKey().longValue());
        }

        // Trả về chính đối tượng đã được cập nhật với ID mới
        return class_OfferingModel;
    }

    @Override
    public Class_OfferingModel update(Class_OfferingModel updateClass_Offering) {
        String sql = "UPDATE class_offering SET offering_code = ?, course_id = ?, semester_id = ?, teacher_id = ?, lms_offering_id = ? WHERE id = ?";
        int row = jdbcTemplate.update(sql, updateClass_Offering.getOfferingCode(), updateClass_Offering.getCourseId(), updateClass_Offering.getSemesterId(),
                updateClass_Offering.getTeacherId(), updateClass_Offering.getLms_Offering_Id(), updateClass_Offering.getId());

        if(row == 0){
            throw new RuntimeException("Không tìm thấy bản ghi để update, id=" + updateClass_Offering.getId());
        }
        return updateClass_Offering;
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM class_offering WHERE id = ?";
        int row = jdbcTemplate.update(sql, id);
        if (row == 0) {
            throw new RuntimeException("Không tìm thấy bản ghi để delete, id=" + id);
        }
    }

    @Override
    public Class_OfferingModel findOne(Long id) {
        String sql = "SELECT co.id, co.offering_code, co.course_id, co.semester_id, co.teacher_id, co.lms_offering_id, cc.title, s.name, u.full_name " +
                " FROM class_offering co join course_catalog cc on co.course_id = cc.id " +
                "join semester s on co.semester_id = s.id " +
                "join user u on co.teacher_id = u.id where co.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Class_OfferingMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Class_OfferingDTO findDetailById(Long id) {
        String sql = "SELECT co.id as lcmsOfferingId, co.offering_code, cc.title as course_title, s.name as semester_name, u.full_name as teacher_name " +
                "FROM class_offering co " +
                "INNER JOIN course_catalog cc ON co.course_id = cc.id " +
                "INNER JOIN semester s ON co.semester_id = s.id " +
                "INNER JOIN user u ON co.teacher_id = u.id " +
                "WHERE co.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ClassOfferingDTOMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
