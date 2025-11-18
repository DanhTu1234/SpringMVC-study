package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.LOVersionModel;
import laptrinhjavaweb.model.LearningObjectModel;

import java.sql.Timestamp;
import java.util.List;

public interface ILearningObjectDAO {
    int insertLearningObject(Long folderId, String title, String description);
    int insertLOVersion(int loId, String versionNumber, String fileName, String filePath, Long fileSizeBytes, String mimeType, String status);
    void updateActionVersion(int loId, int loVersionId);
    List<LearningObjectModel> getLearningObjectByFolder(Long folderId);
    List<LOVersionModel> getLOVersionsByLOId(int loId);
    void archiveOldVersions(int loId, int activeVersionId);
}
