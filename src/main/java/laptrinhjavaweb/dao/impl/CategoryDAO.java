package laptrinhjavaweb.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import laptrinhjavaweb.dao.ICategoryDAO;
import laptrinhjavaweb.mapper.CategoryCourseMapper;
import laptrinhjavaweb.mapper.CourseMapper;
import laptrinhjavaweb.model.CategoryCourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO implements ICategoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public CategoryCourseModel findOneByCode(String code) {
        String sql = "SELECT * FROM categorycourse WHERE code = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CategoryCourseMapper(), code);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}