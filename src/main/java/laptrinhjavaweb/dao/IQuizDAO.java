package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.QuizModel;

import java.util.List;

public interface IQuizDAO {
    List<QuizModel> findAll();
    QuizModel findById(int id);
    QuizModel insert(QuizModel quizModel);
    QuizModel update(QuizModel quizModel);
    int delete(int id);
    QuizModel findReviewByAttemptId(int attemptId);
}
