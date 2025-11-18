package laptrinhjavaweb.dto;

import java.util.List;

public class UserAnswerDTO {
    private Integer questionId;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    private List<Integer> answerId; // có thể null nếu bỏ qua
    // getters và setters

    public List<Integer> getAnswerId() {
        return answerId;
    }

    public void setAnswerId(List<Integer> answerId) {
        this.answerId = answerId;
    }
}