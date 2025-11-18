package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.IDocumentDAO;
import laptrinhjavaweb.dao.ILearningObjectDAO;
import laptrinhjavaweb.model.LOVersionModel;
import laptrinhjavaweb.model.LearningObjectModel;
import laptrinhjavaweb.service.ILeaningObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
public class LearningObjectService implements ILeaningObjectService {
    @Autowired
    private ILearningObjectDAO learningObjectDAO;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private S3Presigner s3Presigner;

    private String s3BucketName = "my-lcms";

    public void uploadLearningObject(Long folderId, String title, String version, String description, MultipartFile file){
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
            int loId = learningObjectDAO.insertLearningObject(folderId, title, description);
            int loVersionId = learningObjectDAO.insertLOVersion(loId, version, storedFileName, fileUrl.toString(), file.getSize(), file.getContentType(), "active");
            learningObjectDAO.updateActionVersion(loId, loVersionId);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file " + e.getMessage());
        }
    }

    public void uploadNewVersion(int loId, String version, MultipartFile file){
        String originalFileName = file.getOriginalFilename();
        String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
        try{
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(s3BucketName)
                    .key(storedFileName)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            URL fileUrl = s3Client.utilities().getUrl(builder -> builder.bucket(s3BucketName).key(storedFileName));

            int loVersionId = learningObjectDAO.insertLOVersion(loId, version, storedFileName, fileUrl.toString(), file.getSize(), file.getContentType(), "active");
            learningObjectDAO.archiveOldVersions(loId, loVersionId);
            learningObjectDAO.updateActionVersion(loId, loVersionId);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file mới" + e.getMessage());
        }
    }

    @Override
    public List<LearningObjectModel> getLearningObjectByFolder(Long folderId) {
        return learningObjectDAO.getLearningObjectByFolder(folderId);
    }

    @Override
    public List<LOVersionModel> getLOVersionsByLOId(int loId) {
        return learningObjectDAO.getLOVersionsByLOId(loId);
    }

    @Override
    public String generatePresignedUrl(String fileName, boolean download) {
        try {
            GetObjectRequest.Builder getObjectRequestBuilder = GetObjectRequest.builder()
                    .bucket(s3BucketName)
                    .key(fileName);

            // Nếu là chế độ tải xuống thì ép header attachment
            if (download) {
                getObjectRequestBuilder.responseContentDisposition("attachment; filename=\"" + fileName + "\"");
            }

            GetObjectRequest getObjectRequest = getObjectRequestBuilder.build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .getObjectRequest(getObjectRequest)
                    .build();

            PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);

            return presignedRequest.url().toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể tạo link tải file: " + e.getMessage());
        }
    }


}
