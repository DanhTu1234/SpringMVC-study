package laptrinhjavaweb.dto;

public class Class_OfferingDTO {
    private Long lcmsOfferingId; // ID từ bảng class_offering
    private String offering_code;
    private String course_title;

    public Long getLcmsOfferingId() {
        return lcmsOfferingId;
    }

    public void setLcmsOfferingId(Long lcmsOfferingId) {
        this.lcmsOfferingId = lcmsOfferingId;
    }

    private String semester_name;

    public String getOffering_code() {
        return offering_code;
    }

    public void setOffering_code(String offering_code) {
        this.offering_code = offering_code;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getSemester_name() {
        return semester_name;
    }

    public void setSemester_name(String semester_name) {
        this.semester_name = semester_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    private String teacher_name;
}
