package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.QuizModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizMapper implements RowMapper<QuizModel> {
    @Override
    public QuizModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizModel quiz = new QuizModel();
        quiz.setId(rs.getInt("id"));
        quiz.setTitle(rs.getString("title"));
        quiz.setDescription(rs.getString("description"));
        quiz.setDuration(rs.getInt("duration"));
        quiz.setCreated_by(rs.getInt("created_by"));
        quiz.setCreated_at(rs.getTimestamp("created_at"));
        return quiz;
    }

}
