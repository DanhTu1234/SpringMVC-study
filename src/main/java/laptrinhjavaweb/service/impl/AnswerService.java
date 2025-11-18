package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.impl.AnswerDAO;
import laptrinhjavaweb.model.AnswerModel;
import laptrinhjavaweb.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AnswerService implements IAnswerService {
    @Autowired
    private AnswerDAO answerDao;

    @Override
    public AnswerModel insert(AnswerModel answerModel) {
        return answerDao.insert(answerModel);
    }

    @Override
    public AnswerModel update(AnswerModel answerModel) {
        return answerDao.update(answerModel);
    }

    @Override
    public int delete(int id) {
        return answerDao.delete(id);
    }

    @Override
    public void deleteAnswersByQuestionId(int questionId) {
        answerDao.deleteAnswersByQuestionId(questionId);
    }

    @Override
    public List<AnswerModel> findByQuestionId(int questionId) {
        return answerDao.findByQuestionId(questionId);
    }
}
