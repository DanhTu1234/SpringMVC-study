package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.IUserDAO;
import laptrinhjavaweb.mapper.SemesterMapper;
import laptrinhjavaweb.mapper.UserMapper;
import laptrinhjavaweb.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO implements IUserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM user";

        return jdbcTemplate.query(sql, new UserMapper());
    }
}
