package laptrinhjavaweb.mapper;

import laptrinhjavaweb.model.DocumentModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentMapper implements RowMapper<DocumentModel> {

    @Override
    public DocumentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentModel documentModel = new DocumentModel();
        documentModel.setId(rs.getLong("id"));
        documentModel.setDisplayName(rs.getString("display_name"));
        documentModel.setFileName(rs.getString("file_name"));
        documentModel.setFilePath(rs.getString("file_path"));
        documentModel.setFileType(rs.getString("file_type"));
        documentModel.setFileSize(rs.getLong("file_size"));
        documentModel.setFolderId(rs.getLong("folder_id"));
        documentModel.setUploaderId(rs.getLong("uploader_id"));

        return documentModel;
    }
}
