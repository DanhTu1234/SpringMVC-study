package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dto.SubmitQuizDTO;
import laptrinhjavaweb.dto.UserAnswerDTO;
import laptrinhjavaweb.service.IQuizAttempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class QuizAttempService implements IQuizAttempService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> startAttempt(int quizId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO quiz_attempts (quiz_id, start_time) VALUES (?, NOW())",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, quizId);
            return ps;
        }, keyHolder);

        int attemptId = keyHolder.getKey().intValue();

        Map<String, Object> result = new HashMap<>();
        result.put("attemptId", attemptId);
        return result;
    }

    public Map<String, Object> submitQuiz(SubmitQuizDTO request) {
        int attemptId = request.getAttemptId();
        int correctCount = 0;
        int durationSecond = request.getDurationSecond();
        int totalQuestions = request.getAnswers().size();

        for (UserAnswerDTO ans : request.getAnswers()) {
            List<Integer> answerId = ans.getAnswerId();
            // Lây đáp án đúng từ của câu hỏi từ db
            String sqlCorrect = "SELECT id FROM answers WHERE question_id = ? AND is_correct = true";
            List<Integer> correctAnswers = jdbcTemplate.queryForList(sqlCorrect, Integer.class, ans.getQuestionId());

            //Phải chọn đúng hết các đáp án đúng và không chọn đáp án sai
            boolean isCorrect =new HashSet<>(correctAnswers).equals(new HashSet<>(answerId));
            if (isCorrect) {
                correctCount++;
            }

            for(Integer answerIdItem : answerId) {
                Boolean ansIsCorrect = correctAnswers.contains(answerIdItem);
                String insertUA = "INSERT INTO user_answers (attempt_id, question_id, answer_id, is_correct) VALUES (?, ?, ?, ?)";
                jdbcTemplate.update(insertUA, attemptId, ans.getQuestionId(), answerIdItem, ansIsCorrect);
            }
        }
        // Cập nhật kết quả tổng hợp
        jdbcTemplate.update(
                "UPDATE quiz_attempts SET correct_count = ?, total_question = ?, end_time = NOW(), duration_second = ? WHERE id = ?",
                correctCount, totalQuestions, durationSecond, attemptId
        );

        // Trả về kết quả để frontend hiển thị
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Nộp bài thành công!");
        result.put("correctCount", correctCount);
        result.put("totalQuestions", totalQuestions);
        result.put("durationSecond", durationSecond);
        result.put("attemptId", attemptId);

        return result;
    }
}
