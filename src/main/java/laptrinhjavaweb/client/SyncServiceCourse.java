package laptrinhjavaweb.client;

import laptrinhjavaweb.dto.Class_OfferingDTO;
import laptrinhjavaweb.dto.CourseDTO;
import laptrinhjavaweb.dto.ResponseClassOfferingDTO;
import laptrinhjavaweb.dto.ResponseCourseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SyncServiceCourse {
    private final RestTemplate restTemplate = new RestTemplate();

    private String MOODLE_URL = "http://localhost/moodleLms/webservice/rest/server.php";

    private String MOODLE_TOKEN = "8ddb2d81f5adebc934803e08373d9b41";

    public Long createMoodleCourse(CourseDTO courseDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "core_course_create_courses");
        params.add("moodlewsrestformat", "json");

        params.add("courses[0][fullname]", courseDTO.getFullname());
        params.add("courses[0][shortname]", courseDTO.getShortname());
        params.add("courses[0][summary]", courseDTO.getSummary());
        params.add("courses[0][categoryid]", courseDTO.getCategoryid().toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());

        try {
            // Moodle trả về một mảng JSON, ta hứng bằng một mảng các đối tượng Response
            ResponseEntity<ResponseCourseDTO[]> response = restTemplate.exchange(MOODLE_URL, HttpMethod.POST, request, ResponseCourseDTO[].class);

            ResponseCourseDTO[] responseBody = response.getBody();
            if (responseBody != null && responseBody.length > 0) {
                return responseBody[0].getId();
            }
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ tạo mới sang Moodle: " + e.getMessage());
        }
        return null;
    }

    public void updateMoodleCourse(CourseDTO courseDTO, Long moodleCourseId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "core_course_update_courses");
        params.add("moodlewsrestformat", "json");
        params.add("courses[0][id]", String.valueOf(moodleCourseId));
        params.add("courses[0][fullname]", courseDTO.getFullname());
        params.add("courses[0][shortname]", courseDTO.getShortname());
        params.add("courses[0][summary]", courseDTO.getSummary());
        params.add("courses[0][categoryid]", courseDTO.getCategoryid().toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());
        try {
            // Với update, chúng ta không cần xử lý body trả về, chỉ cần gọi và mong nó không lỗi
            restTemplate.exchange(MOODLE_URL, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ cập nhật sang Moodle: " + e.getMessage());
        }
    }

    public boolean deleteMoodleCourse(Long moodleCourseId){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "core_course_delete_courses");
        params.add("moodlewsrestformat", "json");
        params.add("courseids[0]", String.valueOf(moodleCourseId));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, new HttpHeaders());

        try {
            restTemplate.exchange(MOODLE_URL, HttpMethod.POST, requestEntity, String.class);
            return true;
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ xóa sang Moodle: " + e.getMessage());
            return false;
        }
    }

}
