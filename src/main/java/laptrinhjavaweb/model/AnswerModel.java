package laptrinhjavaweb.model;

public class AnswerModel {
    private Integer id;
    private Integer question_id;
    private String answer_text;
    private Boolean is_correct;
    private Boolean is_chosen;

    public Boolean getIs_chosen() {
        return is_chosen;
    }

    public void setIs_chosen(Boolean is_chosen) {
        this.is_chosen = is_chosen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }
}
