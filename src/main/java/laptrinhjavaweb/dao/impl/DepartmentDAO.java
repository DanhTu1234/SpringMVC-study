package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.IDepartmentDAO;
import laptrinhjavaweb.mapper.DepartmentMapper;
import laptrinhjavaweb.model.DepartmentModel;
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
import java.util.List;

@Repository
public class DepartmentDAO implements IDepartmentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DepartmentModel> findAll() {
        String sql = "SELECT * FROM department";

        return jdbcTemplate.query(sql, new DepartmentMapper());
    }

    @Override
    public DepartmentModel findOne(Long id){
        String sql = "SELECT * FROM department where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new DepartmentMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Long insert(DepartmentModel departmentModel) {
        String sql = "INSERT INTO department (name) VALUES(?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, departmentModel.getName());
                return ps;
            }
        }, keyHolder);

        // Lấy ID vừa được tạo ra từ KeyHolder
        // keyHolder.getKey() có thể trả về null nếu không có key nào được sinh ra
        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().longValue();
        }
        return null;
    }

    @Override
    public void update(DepartmentModel updateDepartment) {
        String sql = "UPDATE department SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, updateDepartment.getName(), updateDepartment.getId());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM department WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
