package laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class LOVersionModel {
    private Long id;

    @JsonProperty("lo_id")
    private Long loId;

    @JsonProperty("version_number")
    private String versionNumber;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("file_size_bytes")
    private Long fileSizeBytes;

    @JsonProperty("mime_type")
    private String mimeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getFileSizeBytes() {
        return fileSizeBytes;
    }

    public void setFileSizeBytes(Long fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Long getLoId() {
        return loId;
    }

    public void setLoId(Long loId) {
        this.loId = loId;
    }

    private Timestamp createdAt;
}
