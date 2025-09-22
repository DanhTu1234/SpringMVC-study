package laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseSectionModel {
    private Long id;

    @JsonProperty("course_id")
    private Long courseId;
    private Long parent;

    @JsonProperty("lms_section_id")
    private Long lmsSectionId;

    public Long getLmsSectionId() {
        return lmsSectionId;
    }

    public void setLmsSectionId(Long lmsSectionId) {
        this.lmsSectionId = lmsSectionId;
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
