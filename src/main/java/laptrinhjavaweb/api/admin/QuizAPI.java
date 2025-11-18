package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.AnswerModel;
import laptrinhjavaweb.model.QuestionModel;
import laptrinhjavaweb.model.QuizModel;
import laptrinhjavaweb.service.IAnswerService;
import laptrinhjavaweb.service.IQuestionService;
import laptrinhjavaweb.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "classQuizAPI")
@RequestMapping("/api/quizz")
@CrossOrigin("http://127.0.0.1:5500")
public class QuizAPI {
    @Autowired
    private IQuizService quizService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IAnswerService answerService;

    @GetMapping
    public List<QuizModel> getAll() {
        return quizService.findAll();
    }

    @GetMapping("/{id}")
    public QuizModel getById(@PathVariable int id) {
        return quizService.findById(id);
    }

    @GetMapping("/review/{attemptId}")
    public ResponseEntity<QuizModel> reviewAttempt(@PathVariable int attemptId){
        QuizModel quizModel = quizService.findReviewByAttemptId(attemptId);
        return ResponseEntity.ok(quizModel);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try{
            String fileUrl = quizService.uploadFile(file);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e){
            return ResponseEntity.status(500).body("Lỗi tải tệp lên: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QuizModel quizModel) {
        QuizModel savedQuiz = quizService.insert(quizModel);
        for (QuestionModel q : quizModel.getQuestions()) {
            q.setQuiz_id(savedQuiz.getId());
            QuestionModel savedQuestion = questionService.insert(q);

            if (q.getAnswers() != null) {
                for (AnswerModel ans : q.getAnswers()) {
                    ans.setQuestion_id(savedQuestion.getId());
                    answerService.insert(ans);
                }
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Tạo quiz thành công");
        response.put("quiz_id", savedQuiz.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody QuizModel quizModel) {
        quizModel.setId(id);
        quizService.update(quizModel); //Cập nhật db quiz

        QuizModel quiz = quizService.findById(id);  // Lấy quiz, answer, question hiện tại từ db

        //Xóa question ko còn trong quizModel
        for (QuestionModel oldQ : quiz.getQuestions()) {
            //Kiểm tra xem question cũ có trong quizModel ko
            boolean exists = quizModel.getQuestions().stream().filter(q -> q.getId() != null).anyMatch(q -> q.getId().equals(oldQ.getId()));
            if (!exists){
                questionService.delete(oldQ.getId());
            }
        }

        if(quizModel.getQuestions() != null) {
            //Cập nhật hoặc thêm mới question
            for(QuestionModel q : quizModel.getQuestions()){
                q.setQuiz_id(id);

                //Xử lý ảnh
                QuestionModel oldQuestion = questionService.findById(q.getId());
                String oldMediaUrl = oldQuestion != null ? oldQuestion.getMedia_url() : null;
                if(oldMediaUrl != null && q.getMedia_url() == null){
                    //Trường hợp user xóa ảnh
                    quizService.deleteFileFromS3(oldMediaUrl);
                }
                if(oldMediaUrl != null && q.getMedia_url() != null && !q.getMedia_url().equals(oldMediaUrl)){
                    //Trường hợp user thay đổi ảnh
                    quizService.deleteFileFromS3(oldMediaUrl);
                }

                if (q.getId() != null) {
                    questionService.update(q);
                } else {
                    questionService.insert(q);
                }

                List<AnswerModel> oldAnswer = answerService.findByQuestionId(q.getId());
                //Xóa answer ko còn trong quizModel
                for(AnswerModel oldA: oldAnswer){
                    //Kiểm tra xem answer cũ có trong quizModel ko
                    boolean exists = q.getAnswers().stream().filter(a -> a.getId() != null).anyMatch(a -> a.getId().equals(oldA.getId()));
                    if (!exists){
                        answerService.delete(oldA.getId());
                    }
                }

                if (q.getAnswers() != null) {
                    //Cập nhật hoặc thêm mới answer
                    for (AnswerModel a : q.getAnswers()) {
                        a.setQuestion_id(q.getId());
                        if (a.getId() != null) {
                            answerService.update(a);
                        } else {
                            answerService.insert(a);
                        }
                    }
                }
            }
        }
        return ResponseEntity.ok("Cập nhật thành công!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id) {
        int delete = quizService.delete(id);
        return ResponseEntity.ok(delete);
    }
}
