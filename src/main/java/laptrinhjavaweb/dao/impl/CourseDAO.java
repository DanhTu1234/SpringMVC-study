package laptrinhjavaweb.dao.impl;

import java.sql.*;

import java.util.List;

import laptrinhjavaweb.dao.ICourseDAO;
import laptrinhjavaweb.dto.CourseDTO;
import laptrinhjavaweb.mapper.CourseDTOMapper;
import laptrinhjavaweb.mapper.CourseMapper;
import laptrinhjavaweb.model.CourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAO implements ICourseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CourseModel> findAll() {
        String sql = "SELECT course.*, categorycourse.name FROM course join categorycourse on course.category_id = categorycourse.id";

        return jdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public CourseModel insert(CourseModel courseModel) {
        String sql = "INSERT INTO course (category_id, fullname, shortname, summary, created_date, modified_date, created_by, modified_by) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, courseModel.getCategoryCourseId());
                ps.setString(2, courseModel.getFullName());
                ps.setString(3, courseModel.getShortName());
                ps.setString(4, courseModel.getSummary());
                ps.setTimestamp(5, courseModel.getCreatedDate());
                ps.setTimestamp(6, courseModel.getModifiedDate());
                ps.setString(7, courseModel.getCreatedBy());
                ps.setString(8, courseModel.getModifiedBy());
                return ps;
            }
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            courseModel.setId(keyHolder.getKey().longValue());
        }
        return courseModel;
    }

    @Override
    public CourseModel update(CourseModel updateCourse) {
        String sql = "UPDATE course SET category_id = ?, fullname = ?, shortname = ?, summary = ?, created_date = ?, modified_date = ?, created_by = ?, modified_by = ?, lms_course_id = ? WHERE id = ?";
        int row = jdbcTemplate.update(sql, updateCourse.getCategoryCourseId(), updateCourse.getFullName(), updateCourse.getShortName(),
                updateCourse.getSummary(), updateCourse.getCreatedDate(), updateCourse.getModifiedDate(), updateCourse.getCreatedBy(),
                updateCourse.getModifiedBy(), updateCourse.getLmsCourseId(), updateCourse.getId());
        if(row == 0){
            throw new RuntimeException("Không tìm thấy bản ghi");
        }
        return updateCourse;
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM course WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public CourseModel findOne(Long id) {
        String sql = "SELECT course.*, categorycourse.* FROM course join categorycourse on course.category_id = categorycourse.id WHERE course.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CourseMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public CourseDTO findCourseById(long id) {
        String sql = "SELECT cc.lms_category_id as categoryid, c.fullname as fullname, c.shortname as shortname, c.summary as summary " +
                "FROM course c join categorycourse cc on c.category_id = cc.id  WHERE c.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CourseDTOMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
