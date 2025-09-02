package laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Class_OfferingModel {

    private Long id;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("offering_code")
    private String offeringCode;

    private List<Class_OfferingModel> listResult;

    public List<Class_OfferingModel> getListResult() {
        return listResult;
    }

    public void setListResult(List<Class_OfferingModel> listResult) {
        this.listResult = listResult;
    }

    public Long getLms_Offering_Id() {
        return lms_Offering_Id;
    }

    public void setLms_Offering_Id(Long lms_Offering_Id) {
        this.lms_Offering_Id = lms_Offering_Id;
    }

    @JsonProperty("lms_offering_id")
    private Long lms_Offering_Id;
    private Course_CatalogModel course_catalog = new Course_CatalogModel();
    private SemesterModel semester = new SemesterModel();
    private UserModel user = new UserModel();

    public SemesterModel getSemester() {
        return semester;
    }

    public void setSemester(SemesterModel semester) {
        this.semester = semester;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Course_CatalogModel getCourse_catalog() {
        return course_catalog;
    }

    public void setCourse_catalog(Course_CatalogModel course_catalog) {
        this.course_catalog = course_catalog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getOfferingCode() {
        return offeringCode;
    }

    public void setOfferingCode(String offeringCode) {
        this.offeringCode = offeringCode;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @JsonProperty("semester_id")
    private Long semesterId;

    @JsonProperty("teacher_id")
    private Long teacherId;

}
