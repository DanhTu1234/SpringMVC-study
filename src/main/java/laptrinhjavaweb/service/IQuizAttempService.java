package laptrinhjavaweb.service;

import laptrinhjavaweb.dto.SubmitQuizDTO;

import java.util.Map;

public interface IQuizAttempService{
    Map<String, Object> submitQuiz(SubmitQuizDTO request);

    Map<String, Object> startAttempt(int quizId);
}
