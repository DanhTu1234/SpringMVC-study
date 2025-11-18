package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.QuestionModel;
import laptrinhjavaweb.model.QuizModel;

import java.util.List;

public interface IQuestionDAO {
    List<QuestionModel> findAll();
    QuestionModel findById(int id);
    List<QuestionModel> findByQuizId(int quizId);
    QuestionModel insert(QuestionModel questionModel);
    QuestionModel update(QuestionModel questionModel);
    int delete(int id);
}
