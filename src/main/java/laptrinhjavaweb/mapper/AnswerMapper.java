package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.AnswerModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<AnswerModel> {
    @Override
    public AnswerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnswerModel answerModel = new AnswerModel();
        answerModel.setId(rs.getInt("id"));
        answerModel.setQuestion_id(rs.getInt("question_id"));
        answerModel.setAnswer_text(rs.getString("answer_text"));
        answerModel.setIs_correct(rs.getBoolean("is_correct"));
        return answerModel;
    }
}
