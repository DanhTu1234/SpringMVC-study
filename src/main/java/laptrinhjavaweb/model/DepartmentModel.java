package laptrinhjavaweb.model;

import java.util.List;

public class DepartmentModel {
    private Long id;
    private List<DepartmentModel> listResult;

    public List<DepartmentModel> getListResult() {
        return listResult;
    }

    public void setListResult(List<DepartmentModel> listResult) {
        this.listResult = listResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
