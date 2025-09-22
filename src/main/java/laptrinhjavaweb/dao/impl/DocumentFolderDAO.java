package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.IDocumentFolderDAO;
import laptrinhjavaweb.mapper.DocumentFolderMapper;
import laptrinhjavaweb.model.DocumentFolderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Repository
public class DocumentFolderDAO implements IDocumentFolderDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DocumentFolderModel> findAll() {
        String sql = "SELECT * FROM document_folders";
        return jdbcTemplate.query(sql, new DocumentFolderMapper());
    }

    @Override
    public DocumentFolderModel insert(DocumentFolderModel folderModel){
        String sql = "INSERT INTO document_folders (name, parent_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, folderModel.getName());
            if (folderModel.getParentId() != null) {
                ps.setLong(2, folderModel.getParentId());
            } else {
                ps.setNull(2, Types.BIGINT);
            }
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            folderModel.setId(keyHolder.getKey().longValue());
        }
        return folderModel;
    }

}
