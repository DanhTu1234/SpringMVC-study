package laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import laptrinhjavaweb.dao.ICategoryDAO;
import laptrinhjavaweb.dto.CourseCategoryDTO;
import laptrinhjavaweb.mapper.CategoryCourseDTOMapper;
import laptrinhjavaweb.mapper.CategoryCourseMapper;
import laptrinhjavaweb.mapper.CourseDTOMapper;
import laptrinhjavaweb.mapper.CourseMapper;
import laptrinhjavaweb.model.CategoryCourseModel;
import laptrinhjavaweb.model.CourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO implements ICategoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CategoryCourseModel insert(CategoryCourseModel categoryCourseModel) {
        String sql = "INSERT INTO categorycourse (name, parent, description) VALUES(?, ?, ?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, categoryCourseModel.getName());
                ps.setLong(2, categoryCourseModel.getParent());
                ps.setString(3, categoryCourseModel.getDescription());
                return ps;
            }
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            categoryCourseModel.setId(keyHolder.getKey().longValue());
        }
        return categoryCourseModel;
    }

    @Override
    public CategoryCourseModel update(CategoryCourseModel updateCategoryCourse) {
        String sql = "UPDATE categorycourse SET name = ?, parent = ?, description = ?, lms_category_id = ? WHERE id = ?";
        int row = jdbcTemplate.update(sql, updateCategoryCourse.getName(), updateCategoryCourse.getParent(),
                updateCategoryCourse.getDescription(), updateCategoryCourse.getLms_category_id(), updateCategoryCourse.getId());
        if(row == 0){
            throw new RuntimeException("Không tìm thấy bản ghi");
        }
        return updateCategoryCourse;
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM categorycourse WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<CategoryCourseModel> findAll() {
        String sql = "SELECT * FROM categorycourse";

        return jdbcTemplate.query(sql, new CategoryCourseMapper());
    }

    @Override
    public CategoryCourseModel findOne(long id) {
        String sql = "SELECT * FROM categorycourse WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CategoryCourseMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public CourseCategoryDTO findCategoryById(Long id){
        String sql = "SELECT cc.name, cc.parent, cc.description " +
                "FROM categorycourse cc WHERE cc.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CategoryCourseDTOMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}