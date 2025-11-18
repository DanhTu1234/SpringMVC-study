package laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentFolderModel {
    private Long id;
    private String name;
    private Long parentId;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<DocumentFolderModel> getChildren() {
        return children;
    }

    public void setChildren(List<DocumentFolderModel> children) {
        this.children = children;
    }

    private List<DocumentFolderModel> children = new ArrayList<>();
}
