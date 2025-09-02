package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.DepartmentModel;
import laptrinhjavaweb.model.SemesterModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SemesterMapper implements RowMapper<SemesterModel> {
    @Override
    public SemesterModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        SemesterModel semester = new SemesterModel();
        semester.setId(rs.getLong("id"));
        semester.setName(rs.getString("name"));
        semester.setStartDate(rs.getTimestamp("start_date"));
        semester.setEndDate(rs.getTimestamp("end_date"));

        return  semester;
    }
}
