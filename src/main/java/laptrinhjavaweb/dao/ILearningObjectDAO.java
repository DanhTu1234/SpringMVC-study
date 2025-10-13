package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.LOVersionModel;
import laptrinhjavaweb.model.LearningObjectModel;

import java.sql.Timestamp;

public interface ILearningObjectDAO {
    Long insertLearningObject(String title, String description);
    Long insertLOVersion(Long loId, String versionNumber, String fileName, String filePath, Long fileSizeBytes, String mimeType, Timestamp createdAt);
    void updateActionVersion(Long loId, Long loVersionId);
}
