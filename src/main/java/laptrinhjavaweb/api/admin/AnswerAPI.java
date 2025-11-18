package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.AnswerModel;
import laptrinhjavaweb.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "classAnswerAPI")
@RequestMapping("/api/answer")
@CrossOrigin("http://127.0.0.1:5500")
public class AnswerAPI {
    @Autowired
    private IAnswerService answerService;

    @PostMapping
    public ResponseEntity<List<AnswerModel>> create(@RequestBody List<AnswerModel> answerModel) {
        List<AnswerModel> savedAnswer = new ArrayList<>();
        for (AnswerModel a : answerModel) {
            AnswerModel saved = answerService.insert(a);
            savedAnswer.add(saved);
        }
        return ResponseEntity.ok(savedAnswer);
    }
}
