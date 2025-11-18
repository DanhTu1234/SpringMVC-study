package laptrinhjavaweb.dto;

import java.util.List;

public class SubmitQuizDTO {
    private Integer quizId;
    private Integer attemptId;
    private Integer durationSecond;

    public Integer getDurationSecond() {
        return durationSecond;
    }

    public void setDurationSecond(Integer durationSecond) {
        this.durationSecond = durationSecond;
    }

    public Integer getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Integer attemptId) {
        this.attemptId = attemptId;
    }

    private List<UserAnswerDTO> answers;

    public List<UserAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<UserAnswerDTO> answers) {
        this.answers = answers;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

}

