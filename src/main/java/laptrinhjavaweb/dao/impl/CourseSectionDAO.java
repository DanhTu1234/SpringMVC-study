package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.ICourseSectionDAO;
import laptrinhjavaweb.dto.CourseSectionDTO;
import laptrinhjavaweb.mapper.CourseDTOMapper;
import laptrinhjavaweb.mapper.CourseMapper;
import laptrinhjavaweb.mapper.CourseSectionDTOMapper;
import laptrinhjavaweb.mapper.CourseSectionMapper;
import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.model.CourseSectionModel;
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
public class CourseSectionDAO implements ICourseSectionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<CourseSectionModel> findAll() {
        String sql = "SELECT course_sections.name, course.fullname FROM course_sections join course on course_sections.course_id = course.id";
        return jdbcTemplate.query(sql, new CourseSectionMapper());
    }

    @Override
    public CourseSectionModel findOne(Long id) {
        String sql = "SELECT course_sections.*, course.fullname FROM course_sections join course on course_sections.course_id = course.id WHERE course_sections.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CourseSectionMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public CourseSectionModel insert(CourseSectionModel courseSectionModel) {
        String sql = "INSERT INTO course_sections (course_id, parent, name) VALUES(?, ?, ?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, courseSectionModel.getCourseId());
                ps.setLong(2, courseSectionModel.getParent());
                ps.setString(3, courseSectionModel.getName());
                return ps;
            }
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            courseSectionModel.setId(keyHolder.getKey().longValue());
        }
        return courseSectionModel;
    }

    @Override
    public CourseSectionModel update(CourseSectionModel courseSectionModel) {
        String sql = "UPDATE course_sections SET course_id = ?, parent = ?, name = ?, lms_section_id = ? WHERE id = ?";
        int row = jdbcTemplate.update(sql, courseSectionModel.getCourseId(), courseSectionModel.getParent(), courseSectionModel.getName(),
                courseSectionModel.getLmsSectionId(), courseSectionModel.getId());
        if(row == 0){
            throw new RuntimeException("Không tìm thấy bản ghi");
        }
        return courseSectionModel;
    }

    @Override
    public CourseSectionModel updateName(CourseSectionModel courseSectionModel){
        String sql = "UPDATE course_sections SET name = ? WHERE id = ?";
        int row = jdbcTemplate.update(sql,
                courseSectionModel.getName(),
                courseSectionModel.getId());

        if (row == 0) {
            throw new RuntimeException("Không tìm thấy bản ghi");
        }
        return courseSectionModel;
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM course_sections WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public CourseSectionDTO findById(long id) {
        String sql = "SELECT c.lms_course_id as courseid, cs.parent as section, cs.name as name " +
                "FROM course_sections cs join course c on cs.course_id = c.id  WHERE cs.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CourseSectionDTOMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
