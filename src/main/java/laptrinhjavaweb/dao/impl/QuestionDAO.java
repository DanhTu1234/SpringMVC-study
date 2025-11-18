package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.IQuestionDAO;
import laptrinhjavaweb.mapper.QuestionMapper;
import laptrinhjavaweb.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionDAO implements IQuestionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<QuestionModel> findAll() {
        String sql = "SELECT * FROM questions";
        return jdbcTemplate.query(sql, new QuestionMapper());
    }

    @Override
    public QuestionModel findById(int id) {
        String sql = "SELECT * from questions WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new QuestionMapper(), id);
    }

    @Override
    public List<QuestionModel> findByQuizId(int quizId) {
        String sql = "SELECT * from questions WHERE quiz_id = ?";
        return jdbcTemplate.query(sql, new QuestionMapper(), quizId);
    }

    @Override
    public QuestionModel insert(QuestionModel questionModel) {
        String sql = "INSERT INTO questions (quiz_id, content, question_type, media_url) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, questionModel.getQuiz_id());
                ps.setString(2, questionModel.getContent());
                ps.setString(3, questionModel.getQuestion_type());
                ps.setString(4, questionModel.getMedia_url());
                return ps;
            }
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            questionModel.setId(keyHolder.getKey().intValue());
        }
        return questionModel;
    }

    @Override
    public QuestionModel update(QuestionModel questionModel) {
        String sql = "UPDATE questions SET quiz_id=?, content=?, question_type=?, media_url=? WHERE id=?";
        int row = jdbcTemplate.update(sql, questionModel.getQuiz_id(), questionModel.getContent(), questionModel.getQuestion_type(), questionModel.getMedia_url(), questionModel.getId());
        if(row == 0){
            throw new RuntimeException("Không tìm thấy bản ghi");
        }
        return questionModel;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM questions WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
}
