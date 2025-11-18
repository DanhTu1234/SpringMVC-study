package laptrinhjavaweb.service;

import laptrinhjavaweb.model.QuestionModel;

import java.util.List;

public interface IQuestionService {
    List<QuestionModel> findAll();
    QuestionModel findById(int id);
    List<QuestionModel> findByQuizId(int quizId);
    QuestionModel insert(QuestionModel questionModel);
    QuestionModel update(QuestionModel questionModel);
    int delete(int id);
}
