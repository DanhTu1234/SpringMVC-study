package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.IDocumentDAO;
import laptrinhjavaweb.mapper.CategoryCourseMapper;
import laptrinhjavaweb.mapper.DocumentMapper;
import laptrinhjavaweb.model.DocumentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.Collections;
import java.util.List;

@Repository
public class DocumentDAO implements IDocumentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DocumentModel> findAll() {
        String sql = "SELECT * FROM documents";

        return jdbcTemplate.query(sql, new DocumentMapper());
    }

    @Override
    public DocumentModel insert(DocumentModel documentModel){
        String sql = "INSERT INTO documents (display_name, file_name, file_path, file_type, file_size) " +
                "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, documentModel.getDisplayName());
            ps.setString(2, documentModel.getFileName());
            ps.setString(3, documentModel.getFilePath());
            ps.setString(4, documentModel.getFileType());
            ps.setLong(5, documentModel.getFileSize());
//            if (documentModel.getFolderId() != null) {
//                ps.setLong(6, documentModel.getFolderId());
//            } else {
//                ps.setNull(6, Types.BIGINT);
//            }
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            documentModel.setId(keyHolder.getKey().longValue());
        }
        return documentModel;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM documents WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


}
