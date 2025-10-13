package laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class LearningObjectModel {
    private Long id;

    @JsonProperty("title")
    private String Title;

    @JsonProperty("description")
    private String Description;

    @JsonProperty("active_version_id")
    private Long activeVersionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getActiveVersionId() {
        return activeVersionId;
    }

    public void setActiveVersionId(Long activeVersionId) {
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
