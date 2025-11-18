package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.dto.SubmitQuizDTO;
import laptrinhjavaweb.service.IQuizAttempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController(value = "classQuizAttempAPI")
@RequestMapping("/api/attemp")
@CrossOrigin("http://127.0.0.1:5500")
public class QuizAttempAPI {
    @Autowired
    private IQuizAttempService quizAttemptService;

    @PostMapping("/start")
    public ResponseEntity<?> startAttempt(@RequestBody SubmitQuizDTO request) {
        Map<String, Object> result = quizAttemptService.startAttempt(request.getQuizId());
        return ResponseEntity.ok(result);
    }


    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(@RequestBody SubmitQuizDTO request) {
        Map<String, Object> result = quizAttemptService.submitQuiz(request);
        return ResponseEntity.ok(result);
    }
}
