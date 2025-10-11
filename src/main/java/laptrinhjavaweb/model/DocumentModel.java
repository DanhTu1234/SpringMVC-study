package laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentModel {
    private Long id;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("file_type")
    private String fileType;

    @JsonProperty("uploader_id")
    private Long uploaderId;

    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    @JsonProperty("file_size")
    private Long fileSize;

    @JsonProperty("folder_id")
    private Long folderId;
}
