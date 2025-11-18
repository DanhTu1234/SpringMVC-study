package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.AnswerModel;
import laptrinhjavaweb.model.QuestionModel;

import java.util.List;

public interface IAnswerDAO {
    AnswerModel insert(AnswerModel answerModel);
    AnswerModel update(AnswerModel answerModel);
    int delete(int id);
    void deleteAnswersByQuestionId(int questionId);
    List<AnswerModel> findByQuestionId(int questionId);
}
