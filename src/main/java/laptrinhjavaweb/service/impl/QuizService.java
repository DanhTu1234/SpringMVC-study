package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.IQuestionDAO;
import laptrinhjavaweb.dao.impl.QuizDAO;
import laptrinhjavaweb.model.QuestionModel;
import laptrinhjavaweb.model.QuizModel;
import laptrinhjavaweb.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class QuizService implements IQuizService {
    @Autowired
    private QuizDAO quizDao;

    @Autowired
    private IQuestionDAO questionDAO;

    @Autowired
    private S3Client s3Client;

    private String s3BucketName = "my-lcms";

    @Override
    public List<QuizModel> findAll() {
        return quizDao.findAll();
    }

    @Override
    public QuizModel findById(int id) {
        return quizDao.findById(id);
    }

    @Override
    public QuizModel insert(QuizModel quizModel) {
        return quizDao.insert(quizModel);
    }

    @Override
    public QuizModel update(QuizModel quizModel) {
        return quizDao.update(quizModel);
    }

    @Override
    public int delete(int id) {
        return quizDao.delete(id);
    }

    @Override
    public QuizModel findReviewByAttemptId(int attemptId) {
        return quizDao.findReviewByAttemptId(attemptId);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

        try {
            // Cấu hình upload lên S3
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(s3BucketName)
                    .key(storedFileName)
                    .contentType(file.getContentType())
                    .build();

            // Upload ảnh
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            // Lấy URL công khai của file
            URL fileUrl = s3Client.utilities().getUrl(builder ->
                    builder.bucket(s3BucketName).key(storedFileName));

            return fileUrl.toString();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload ảnh: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFileFromS3(String fileUrl) {
        try {
            String fileKey = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(s3BucketName)
                    .key(fileKey)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
            System.out.println("Đã xóa ảnh cũ trên S3: " + fileKey);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa ảnh cũ: " + e.getMessage());
        }
    }

}
