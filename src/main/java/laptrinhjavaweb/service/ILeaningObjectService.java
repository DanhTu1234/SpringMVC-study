package laptrinhjavaweb.service;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public interface ILeaningObjectService {
    void uploadLearningObject(String title, String version, String description, Timestamp createdAt, MultipartFile file);
}
