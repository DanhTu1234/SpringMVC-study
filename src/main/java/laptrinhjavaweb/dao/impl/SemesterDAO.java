package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.ISemesterDAO;
import laptrinhjavaweb.mapper.DepartmentMapper;
import laptrinhjavaweb.mapper.SemesterMapper;
import laptrinhjavaweb.model.SemesterModel;
import laptrinhjavaweb.service.ISemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class SemesterDAO implements ISemesterDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SemesterModel> findAll() {
        String sql = "SELECT * FROM semester";

        return jdbcTemplate.query(sql, new SemesterMapper());
    }
}
