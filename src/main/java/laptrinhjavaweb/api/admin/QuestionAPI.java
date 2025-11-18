package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.QuestionModel;
import laptrinhjavaweb.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "classQuestionAPI")
@RequestMapping("/api/question")
@CrossOrigin("http://127.0.0.1:5500")
public class QuestionAPI {
    @Autowired
    private IQuestionService questionService;

    @GetMapping
    public List<QuestionModel> getAll() {
        return questionService.findAll();
    }

    @GetMapping("/{id}")
    public List<QuestionModel> getByQuizId(@PathVariable int id) {
        return questionService.findByQuizId(id);
    }

    @PostMapping
    public ResponseEntity<List<QuestionModel>> create(@RequestBody List<QuestionModel> questionModels) {
        List<QuestionModel> savedQuestions = new ArrayList<>();
        for (QuestionModel q : questionModels) {
            QuestionModel saved = questionService.insert(q);
            savedQuestions.add(saved);
        }
        return ResponseEntity.ok(savedQuestions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody List<QuestionModel> questionModel) {
        for(QuestionModel question : questionModel) {
            question.setQuiz_id(id);
            if (question.getId() != null) {
                questionService.update(question);
            } else {
                questionService.insert(question); // thêm mới
            }
        }
        return ResponseEntity.ok("Question updated successfully!");
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        questionService.delete(id);
        return "Question deleted successfully!";
    }
}
