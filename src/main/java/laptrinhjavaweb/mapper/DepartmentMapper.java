package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.DepartmentModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<DepartmentModel> {
    public DepartmentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        DepartmentModel department = new DepartmentModel();
        department.setId(rs.getLong("id"));
        department.setName(rs.getString("name"));
        return department;
    }
}
