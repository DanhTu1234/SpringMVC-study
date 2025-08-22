package laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;


public class CourseModel {

    private Long id;

    @JsonProperty("category_id")
    private Long categoryCourseId;

    public Long getCategoryCourseId() {
        return categoryCourseId;
    }

    public void setCategoryCourseId(Long categoryCourseId) {
        this.categoryCourseId = categoryCourseId;
    }

    public Long getLmsCourseId() {
        return lmsCourseId;
    }

    public void setLmsCourseId(Long lmsCourseId) {
        this.lmsCourseId = lmsCourseId;
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

    private Long lmsCourseId;

    @JsonProperty("fullname")
    private String fullName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("shortname")
    private String shortName;

    private String summary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private List<CourseModel> listResult;

    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String createdBy;
    private String modifiedBy;


    public List<CourseModel> getListResult() {
        return listResult;
    }
    public void setListResult(List<CourseModel> listResult) {
        this.listResult = listResult;
    }


}