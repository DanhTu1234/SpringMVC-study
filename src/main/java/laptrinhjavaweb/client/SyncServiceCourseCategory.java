package laptrinhjavaweb.client;

import laptrinhjavaweb.dto.CourseCategoryDTO;
import laptrinhjavaweb.dto.CourseDTO;
import laptrinhjavaweb.dto.ResponseCourseCategoryDTO;
import laptrinhjavaweb.dto.ResponseCourseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SyncServiceCourseCategory {
    private final RestTemplate restTemplate = new RestTemplate();

    private String MOODLE_URL = "http://localhost/moodleLms/webservice/rest/server.php";

    private String MOODLE_TOKEN = "8ddb2d81f5adebc934803e08373d9b41";

    public List<ResponseCourseCategoryDTO> getAllMoodleCategories() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "core_course_get_categories");
        params.add("moodlewsrestformat", "json");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());

        try {
            // Moodle trả về một MẢNG các đối tượng category
            ResponseEntity<ResponseCourseCategoryDTO[]> response = restTemplate.exchange(
                    MOODLE_URL,
                    HttpMethod.POST,
                    request,
                    ResponseCourseCategoryDTO[].class
            );

            ResponseCourseCategoryDTO[] responseBody = response.getBody();
            if (responseBody != null) {
                return Arrays.asList(responseBody);
            }
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi lấy danh sách category từ Moodle: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public Long createMoodleCourseCategory(CourseCategoryDTO courseCategoryDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "core_course_create_categories");
        params.add("moodlewsrestformat", "json");

        params.add("categories[0][name]", courseCategoryDTO.getName());
        //params.add("categories[0][parent]", courseCategoryDTO.getParent().toString());
        params.add("categories[0][description]", courseCategoryDTO.getDescription());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());

        try {
            // Moodle trả về một mảng JSON, ta hứng bằng một mảng các đối tượng Response
            ResponseEntity<ResponseCourseCategoryDTO[]> response = restTemplate.exchange(MOODLE_URL, HttpMethod.POST, request, ResponseCourseCategoryDTO[].class);

            ResponseCourseCategoryDTO[] responseBody = response.getBody();
            if (responseBody != null && responseBody.length > 0) {
                return responseBody[0].getId();
            }
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ tạo mới sang Moodle: " + e.getMessage());
        }
        return null;
    }

    public void updateMoodleCourseCategory(CourseCategoryDTO courseCategoryDTO, Long moodleCourseCategoryId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "core_course_update_categories");
        params.add("moodlewsrestformat", "json");
        params.add("categories[0][id]", String.valueOf(moodleCourseCategoryId));
        params.add("categories[0][name]", courseCategoryDTO.getName());
        //params.add("categories[0][parent]", courseCategoryDTO.getParent().toString());
        params.add("categories[0][description]", courseCategoryDTO.getDescription());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());
        try {
            restTemplate.exchange(MOODLE_URL, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ cập nhật sang Moodle: " + e.getMessage());
        }
    }

    public boolean deleteMoodleCourseCategory(Long moodleCourseCategoryId){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "core_course_delete_categories");
        params.add("moodlewsrestformat", "json");
        params.add("ids[0]", String.valueOf(moodleCourseCategoryId));

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
