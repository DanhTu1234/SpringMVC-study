package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.DocumentFolderModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentFolderMapper implements RowMapper<DocumentFolderModel> {

    @Override
    public DocumentFolderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentFolderModel folder = new DocumentFolderModel();
        folder.setId(rs.getLong("id"));
        folder.setName(rs.getString("name"));
        folder.setParentId(rs.getObject("parent_id", Long.class)); // Lấy cả giá trị NULL
        return folder;
    }
}
