package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.client.SyncServiceUser;
import laptrinhjavaweb.dto.ResponseEnrollUserDTO;
import laptrinhjavaweb.service.IErollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ErollmentService implements IErollmentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SyncServiceUser syncServiceUser;

    @Transactional
    @Override
    public String syncAllEnrollments() {
        String sqlGetCourses = "SELECT id, lms_course_id FROM course";
        List<Map<String, Object>> courses = jdbcTemplate.queryForList(sqlGetCourses);

        int totalEnrollment = 0;
        int courseCount = 0;

        for (Map<String, Object> course : courses) {
            Long localCourseId = (Long) course.get("id");
            Long lmsCourseId = (Long) course.get("lms_course_id");
            if(lmsCourseId == null) {
                continue;
            }
            String sqlDelete = "DELETE FROM enrollments WHERE course_id = ?";
            jdbcTemplate.update(sqlDelete, localCourseId);

            List<ResponseEnrollUserDTO> enroll = syncServiceUser.getEnrollUserInCourse(lmsCourseId);

            for (ResponseEnrollUserDTO user : enroll) {
                Long lmsUserId = user.getId();
                String roleName = user.getRoles().get(0).getShortname();

                Long localUserId;
                try{
                    String sqlGetUser = "SELECT id FROM users WHERE lms_user_id = ?";
                    localUserId = jdbcTemplate.queryForObject(sqlGetUser, Long.class, lmsUserId);
                } catch (Exception e) {
                    System.err.println(" Lỗi khi tìm User:" + e.getMessage());
                    continue;
                }

                Long localRoleId;
                try{
                    String sqlGetRole = "SELECT id FROM roles WHERE name = ?";
                    localRoleId = jdbcTemplate.queryForObject(sqlGetRole, Long.class, roleName);
                } catch (Exception e) {
                    System.err.println(" Lỗi khi tìm Role:" + e.getMessage());
                    continue;
                }

                String sqlInsertEnrollment = "INSERT INTO enrollments (user_id, course_id, role_id) VALUES (?, ?, ?)";
                jdbcTemplate.update(sqlInsertEnrollment, localUserId, localCourseId, localRoleId);
                totalEnrollment++;
            }
            courseCount++;
        }
        return "Đông bộ hoàn tất. Số khóa học: " + courseCount + ", Số lượt ghi danh: " + totalEnrollment;
    }

}
