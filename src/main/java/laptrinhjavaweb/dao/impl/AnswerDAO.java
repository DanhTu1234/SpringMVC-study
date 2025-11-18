package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.IAnswerDAO;
import laptrinhjavaweb.mapper.AnswerMapper;
import laptrinhjavaweb.mapper.QuestionMapper;
import laptrinhjavaweb.model.AnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class AnswerDAO implements IAnswerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public AnswerModel insert(AnswerModel answerModel) {
        String sql = "INSERT INTO answers (question_id, answer_text, is_correct) VALUES (?, ?, ?)";
        int row = jdbcTemplate.update(sql, answerModel.getQuestion_id(), answerModel.getAnswer_text(), answerModel.getIs_correct());
        if(row == 0){
            throw new RuntimeException("Không tìm thấy bản ghi");
        }
        return answerModel;
    }

    @Override
    public AnswerModel update(AnswerModel answerModel) {
        String sql = "UPDATE answers SET question_id=?, answer_text=?, is_correct=? WHERE id=?";
        int row = jdbcTemplate.update(sql, answerModel.getQuestion_id(), answerModel.getAnswer_text(), answerModel.getIs_correct(), answerModel.getId());
        if(row == 0){
            throw new RuntimeException("Không tìm thấy bản ghi");
        }
        return answerModel;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM answers WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAnswersByQuestionId(int questionId) {
        String sql = "DELETE FROM answers WHERE question_id = ?";
        jdbcTemplate.update(sql, questionId);
    }

    @Override
    public List<AnswerModel> findByQuestionId(int questionId) {
        String sql = "SELECT * from answers WHERE question_id = ?";
        return jdbcTemplate.query(sql, new AnswerMapper(), questionId);
    }
}
