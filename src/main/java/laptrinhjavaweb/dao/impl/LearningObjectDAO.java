package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.ILearningObjectDAO;
import laptrinhjavaweb.mapper.LearningObjectMapper;
import laptrinhjavaweb.model.LOVersionModel;
import laptrinhjavaweb.model.LearningObjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class LearningObjectDAO implements ILearningObjectDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LearningObjectModel> getLearningObjectByFolder(Long folderId) {
        String sql = "SELECT lo.id, lo.title, lo.description, lo.created_at, lo.updated_at, v.version_number FROM learning_objects lo LEFT JOIN lo_versions v ON lo.active_version_id = v.id WHERE lo.folder_id = ?";
        return jdbcTemplate.query(sql, new LearningObjectMapper(), folderId);
    }

    public List<LOVersionModel> getLOVersionsByLOId(int loId) {
        String sql = "SELECT id, version_number, file_name, file_path, file_size_bytes, mime_type, status, created_at FROM lo_versions WHERE lo_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            LOVersionModel version = new LOVersionModel();
            version.setId(rs.getInt("id"));
            version.setVersionNumber(rs.getString("version_number"));
            version.setFileName(rs.getString("file_name"));
            version.setFilePath(rs.getString("file_path"));
            version.setFileSizeBytes(rs.getLong("file_size_bytes"));
            version.setMimeType(rs.getString("mime_type"));
            version.setStatus(rs.getString("status"));
            version.setCreatedAt(rs.getTimestamp("created_at"));
            return version;
        }, loId);
    }

    @Override
    public int insertLearningObject(Long folderId, String title, String description) {
        String sql = "INSERT INTO learning_objects (folder_id, title, description) VALUES(?, ?, ?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, folderId);
                ps.setString(2, title);
                ps.setString(3, description);
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int insertLOVersion(int loId, String versionNumber, String fileName, String filePath, Long fileSizeBytes, String mimeType, String status) {
        String sql = "INSERT INTO lo_versions (lo_id, version_number, file_name, file_path, file_size_bytes, mime_type, status) VALUES(?, ?, ?, ?, ?, ?, ?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, loId);
                ps.setString(2, versionNumber);
                ps.setString(3, fileName);
                ps.setString(4, filePath);
                ps.setLong(5, fileSizeBytes);
                ps.setString(6, mimeType);
                ps.setString(7, status);
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateActionVersion(int loId, int loVersionId) {
        String sql = "UPDATE learning_objects SET active_version_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, loVersionId, loId);
    }

    @Override
    public void archiveOldVersions(int loId, int activeVersionId) {
        String sql = "UPDATE lo_versions SET status = 'archived' WHERE lo_id = ? AND id <> ?";
        jdbcTemplate.update(sql, loId, activeVersionId);
    }
}
