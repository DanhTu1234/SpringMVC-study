package laptrinhjavaweb.dao.impl;

import laptrinhjavaweb.dao.ILearningObjectDAO;
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

@Repository
public class LearningObjectDAO implements ILearningObjectDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long insertLearningObject(String title, String description) {
        String sql = "INSERT INTO learning_objects (title, description) VALUES(?, ?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setString(2, description);
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Long insertLOVersion(Long loId, String versionNumber, String fileName, String filePath, Long fileSizeBytes, String mimeType, java.sql.Timestamp createdAt) {
        String sql = "INSERT INTO lo_versions (lo_id, version_number, file_name, file_path, file_size_bytes, mime_type, created_at) VALUES(?, ?, ?, ?, ?, ?, ?)";

        // KeyHolder dùng để giữ ID tự tăng được sinh ra sau khi insert
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Sử dụng PreparedStatementCreator để gán tham số
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, loId);
                ps.setString(2, versionNumber);
                ps.setString(3, fileName);
                ps.setString(4, filePath);
                ps.setLong(5, fileSizeBytes);
                ps.setString(6, mimeType);
                ps.setTimestamp(7, createdAt);
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateActionVersion(Long loId, Long loVersionId) {
        String sql = "UPDATE learning_objects SET active_version_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, loVersionId, loId);
    }
}
