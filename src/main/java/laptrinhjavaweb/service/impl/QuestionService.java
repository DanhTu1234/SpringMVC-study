package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.impl.QuestionDAO;
import laptrinhjavaweb.model.QuestionModel;
import laptrinhjavaweb.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public List<QuestionModel> findAll() {
        return questionDAO.findAll();
    }

    @Override
    public QuestionModel findById(int id) {
        return  questionDAO.findById(id);
    }

    @Override
    public List<QuestionModel> findByQuizId(int quizId) {
        return questionDAO.findByQuizId(quizId);
    }

    @Override
    public QuestionModel insert(QuestionModel questionModel) {
        return questionDAO.insert(questionModel);
    }

    @Override
    public QuestionModel update(QuestionModel questionModel) {
        return questionDAO.update(questionModel);
    }

    @Override
    public int delete(int id) {
        return questionDAO.delete(id);
    }
}
