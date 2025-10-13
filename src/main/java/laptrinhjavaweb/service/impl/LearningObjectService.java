package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.IDocumentDAO;
import laptrinhjavaweb.dao.ILearningObjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;

@Service
public class LearningObjectService {
    @Autowired
    private ILearningObjectDAO learningObjectDAO;

    @Autowired
    private S3Client s3Client;

    private String s3BucketName = "my-lcms";

    public void uploadLearningObject(String title, String version, String description, Timestamp createdAt, MultipartFile file){
        String originalFileName = file.getOriginalFilename(); // Tên file gốc upload
        String storedFileName = System.currentTimeMillis() + "_" + originalFileName;  // Tạo tên mới để lưu trên S3
        try {
            // Chuẩn bị yêu cầu upload lên S3
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(s3BucketName)
                    .key(storedFileName)
                    .contentType(file.getContentType())
                    .build();

            // Thực hiện upload
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            // Lấy URL công khai của file vừa upload
            URL fileUrl = s3Client.utilities().getUrl(builder -> builder.bucket(s3BucketName).key(storedFileName));

            // Lưu thông tin Learning Object và LO Version vào database
            Long loId = learningObjectDAO.insertLearningObject(title, description);
            Long loVersionId = learningObjectDAO.insertLOVersion(loId, version, originalFileName, fileUrl.toString(), file.getSize(), file.getContentType(), createdAt);
            learningObjectDAO.updateActionVersion(loId, loVersionId);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file " + e.getMessage());
        }
    }
}
