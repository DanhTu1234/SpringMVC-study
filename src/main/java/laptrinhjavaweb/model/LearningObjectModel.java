package laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LearningObjectModel {
    private Integer id;

    @JsonProperty("folder_id")
    private Long folderId;

    @JsonProperty("title")
    private String Title;

    @JsonProperty("description")
    private String Description;

    @JsonProperty("active_version_id")
    private Integer activeVersionId;

    private LOVersionModel loVersion = new LOVersionModel();

    public LOVersionModel getLoVersion() {
        return loVersion;
    }

    public void setLoVersion(LOVersionModel loVersion) {
        this.loVersion = loVersion;
    }

    private DocumentFolderModel documentFolder = new DocumentFolderModel();

    public DocumentFolderModel getDocumentFolder() {
        return documentFolder;
    }

    public void setDocumentFolder(DocumentFolderModel documentFolder) {
        this.documentFolder = documentFolder;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActiveVersionId() {
        return activeVersionId;
    }

    public void setActiveVersionId(Integer activeVersionId) {
        this.activeVersionId = activeVersionId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
