package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserModel user = new UserModel();
        user.setId(rs.getLong("id"));
        user.setUserName(rs.getString("user_name"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPassWord(rs.getString("password"));
        user.setRoleId(rs.getLong("role_id"));
        user.setDepartmentId(rs.getLong("department_id"));
        user.setClassId(rs.getLong("class_id"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));

        return user;
    }
}
