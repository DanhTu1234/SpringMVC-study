package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.ICourseCatalogDAO;
import laptrinhjavaweb.mapper.CourseCatalogMapper;
import laptrinhjavaweb.mapper.DepartmentMapper;
import laptrinhjavaweb.model.Course_CatalogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CourseCatalogDAO implements ICourseCatalogDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Course_CatalogModel> findAll() {
        String sql = "SELECT * FROM course_catalog";

        return jdbcTemplate.query(sql, new CourseCatalogMapper());
    }
}
