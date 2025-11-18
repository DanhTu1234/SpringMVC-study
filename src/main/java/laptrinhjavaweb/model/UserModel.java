package laptrinhjavaweb.model;

public class UserModel {
    private Integer id;
    private String username;
    private String email;
    private String full_name;
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getLms_user_id() {
        return lms_user_id;
    }

    public void setLms_user_id(Long lms_user_id) {
        this.lms_user_id = lms_user_id;
    }

    private Long lms_user_id;
}
