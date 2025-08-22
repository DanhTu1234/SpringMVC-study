package laptrinhjavaweb.model;

import java.sql.Timestamp;

public class CategoryNewModel {

    private Long id;
    private String name;
    private String code;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String createdBy;
    private String modifiedBy;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
