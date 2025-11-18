package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.QuestionModel;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<QuestionModel> {

    @Override
    public QuestionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuestionModel question = new QuestionModel();
        question.setId(rs.getInt("id"));
        question.setQuiz_id(rs.getInt("quiz_id"));
        //question.getQuiz().setTitle(rs.getString("title"));
        question.setContent(rs.getString("content"));
        question.setQuestion_type(rs.getString("question_type"));
        question.setMedia_url(rs.getString("media_url"));
        return question;
    }

}
