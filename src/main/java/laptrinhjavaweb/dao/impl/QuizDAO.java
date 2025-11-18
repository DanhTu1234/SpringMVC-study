package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.IQuizDAO;
import laptrinhjavaweb.mapper.AnswerMapper;
import laptrinhjavaweb.mapper.QuestionMapper;
import laptrinhjavaweb.mapper.QuizMapper;
import laptrinhjavaweb.model.AnswerModel;
import laptrinhjavaweb.model.QuestionModel;
import laptrinhjavaweb.model.QuizModel;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QuizDAO implements IQuizDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<QuizModel> findAll() {
        String sql = "SELECT * FROM quizzes";
        return jdbcTemplate.query(sql, new QuizMapper());
    }

    @Override
    public QuizModel findById(int id) {
        // Lấy thông tin quiz
        String quizSql = "SELECT * FROM quizzes WHERE id = ?";
        QuizModel quiz = jdbcTemplate.queryForObject(quizSql, new QuizMapper(), id);

        // Lấy danh sách câu hỏi
        String questionSql = "SELECT * FROM questions WHERE quiz_id = ?";
        List<QuestionModel> questions = jdbcTemplate.query(questionSql, new QuestionMapper(), id);

        //Lấy danh sách câu trả lời
        String answerSql = "SELECT * FROM answers WHERE question_id = ?";
        for (QuestionModel q : questions) {
            List<AnswerModel> answers = jdbcTemplate.query(answerSql, new AnswerMapper(), q.getId());
            q.setAnswers(answers);
        }

        // Gắn danh sách câu hỏi vào quiz
        quiz.setQuestions(questions);

        return quiz;
    }

    @Override
    public QuizModel insert(QuizModel quizModel) {
        String sql = "INSERT INTO quizzes (title, description, duration) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, quizModel.getTitle());
                ps.setString(2, quizModel.getDescription());
                ps.setInt(3, quizModel.getDuration());
                //ps.setInt(4, quizModel.getCreated_by());
                return ps;
            }
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            quizModel.setId(keyHolder.getKey().intValue());
        }
        return quizModel;
    }

    @Override
    public QuizModel update(QuizModel quizModel) {
        String sql = "UPDATE quizzes SET title=?, description=?, duration=? WHERE id=?";
        int row = jdbcTemplate.update(sql, quizModel.getTitle(), quizModel.getDescription(), quizModel.getDuration(), quizModel.getId());
        if(row == 0){
            throw new RuntimeException("Không tìm thấy bản ghi");
        }
        return quizModel;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM quizzes WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public QuizModel findReviewByAttemptId(int attemptId) {
        // Lấy thông tin quiz
        String quizSql = "SELECT q.* FROM quizzes q JOIN quiz_attempts qa ON qa.quiz_id = q.id WHERE qa.id = ?";
        QuizModel quiz = jdbcTemplate.queryForObject(quizSql, new QuizMapper(), attemptId);

        // Lấy danh sách câu hỏi
        String questionSql = "SELECT q.* FROM questions q JOIN quiz_attempts qa ON qa.quiz_id = q.quiz_id WHERE qa.id = ? ORDER BY q.id";
        List<QuestionModel> questions = jdbcTemplate.query(questionSql, new QuestionMapper(), attemptId);

        // Lấy danh sách câu trả lời và đánh dấu câu trả lời đã chọn
        String answerSql = "SELECT a.*, ua.answer_id AS chosen_id FROM answers a LEFT JOIN user_answers ua ON ua.answer_id = a.id AND ua.attempt_id = ? WHERE a.question_id = ? ORDER BY a.id";

        for (QuestionModel q : questions) {
            List<AnswerModel> answers = jdbcTemplate.query(answerSql, (rs, rowNum) -> {
                AnswerModel a = new AnswerModel();
                a.setId(rs.getInt("id"));
                a.setAnswer_text(rs.getString("answer_text"));
                a.setIs_correct(rs.getBoolean("is_correct"));
                Integer chosenId = rs.getObject("chosen_id", Integer.class);
                a.setIs_chosen(chosenId != null && chosenId == a.getId());
                return a;
            }, attemptId, q.getId());
            q.setAnswers(answers);
        }

        // Gắn câu hỏi vào quiz
        quiz.setQuestions(questions);

        return quiz;
    }
}
