package laptrinhjavaweb.service;

import laptrinhjavaweb.model.LOVersionModel;
import laptrinhjavaweb.model.LearningObjectModel;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

public interface ILeaningObjectService {
    void uploadLearningObject(Long folderId, String title, String version, String description, MultipartFile file);
    void uploadNewVersion(int loId, String version, MultipartFile file);
    List<LearningObjectModel> getLearningObjectByFolder(Long folderId);
    List<LOVersionModel> getLOVersionsByLOId(int loId);
    String generatePresignedUrl(String fileName, boolean download);
}
