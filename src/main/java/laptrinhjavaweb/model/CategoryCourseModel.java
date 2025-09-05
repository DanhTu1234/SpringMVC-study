package laptrinhjavaweb.model;


public class CategoryCourseModel {

    private Long id;
    private String name;
    private Long parent;
    private Long lms_category_id;

    public Long getLms_category_id() {
        return lms_category_id;
    }

    public void setLms_category_id(Long lms_category_id) {
        this.lms_category_id = lms_category_id;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
