package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.IDocumentDAO;
import laptrinhjavaweb.model.DocumentModel;
import laptrinhjavaweb.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;

@Service
public class DocumentService implements IDocumentService {
    @Autowired
    private IDocumentDAO documentDAO;

    @Autowired
    private S3Client s3Client;

    private String s3BucketName = "my-lcms";

    @Override
    public DocumentModel uploadFile(MultipartFile file, Long folderId) {
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

            // Tạo đối tượng lưu db
            DocumentModel documentModel = new DocumentModel();
            documentModel.setDisplayName(originalFileName);
            documentModel.setFileName(storedFileName);
            documentModel.setFilePath(fileUrl.toString());
            documentModel.setFileType(file.getContentType());
            documentModel.setFileSize(file.getSize());
            documentModel.setFolderId(folderId);

            return documentDAO.insert(documentModel);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi upload file " + e.getMessage());
        }
    }
}
