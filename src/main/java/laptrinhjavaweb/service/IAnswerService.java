package laptrinhjavaweb.service;

import laptrinhjavaweb.model.AnswerModel;

import java.util.List;

public interface IAnswerService {
    AnswerModel insert(AnswerModel answerModel);
    AnswerModel update(AnswerModel answerModel);
    int delete(int id);
    void deleteAnswersByQuestionId(int questionId);
    List<AnswerModel> findByQuestionId(int questionId);
}
