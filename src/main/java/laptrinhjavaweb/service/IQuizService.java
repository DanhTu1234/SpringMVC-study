package laptrinhjavaweb.service;

import laptrinhjavaweb.model.QuizModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IQuizService {
    List<QuizModel> findAll();
    QuizModel findById(int id);
    QuizModel insert(QuizModel quizModel);
    QuizModel update(QuizModel quizModel);
    int delete(int id);
    QuizModel findReviewByAttemptId(int attemptId);
    String uploadFile(MultipartFile file);
    void deleteFileFromS3(String fileUrl);
}
