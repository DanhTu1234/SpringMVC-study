package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.client.SyncServiceUser;
import laptrinhjavaweb.dto.ResponseCourseDTO;
import laptrinhjavaweb.dto.ResponseUserDTO;
import laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private SyncServiceUser syncServiceUser;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String syncAllUserFromMoodle() {
        List<ResponseUserDTO> moodleUser = syncServiceUser.getAllMoodleUsers();
        int updateCount = 0;
        int insertCount = 0;
        for (ResponseUserDTO dto : moodleUser) {
            String updateSql = "UPDATE users SET username = ?, full_name = ?, email=?, role=? WHERE lms_user_id = ?";
            int row = jdbcTemplate.update(updateSql, dto.getUsername(), dto.getFullname(), dto.getEmail(), dto.getRole(), dto.getId());
            if (row == 0) {
                String insertSql = "INSERT INTO users (username, full_name, email, role, lms_user_id) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(insertSql, dto.getUsername(), dto.getFullname(), dto.getEmail(), dto.getRole(), dto.getId());
                insertCount++;
            } else {
                updateCount++;
            }
        }
        return "Đồng bộ hoàn tất. Cập nhật: " + updateCount + ", Thêm mới: " + insertCount;
    }
}
