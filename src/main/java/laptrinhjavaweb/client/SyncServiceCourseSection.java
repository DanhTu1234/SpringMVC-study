package laptrinhjavaweb.client;

import laptrinhjavaweb.dto.CourseSectionDTO;
import laptrinhjavaweb.dto.ResponseCourseSectionDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SyncServiceCourseSection {
    private final RestTemplate restTemplate = new RestTemplate();

    private String MOODLE_URL = "http://localhost/moodleLms/webservice/rest/server.php";

    private String MOODLE_TOKEN = "8ddb2d81f5adebc934803e08373d9b41";

    public Long createMoodleCourseSection(CourseSectionDTO courseSectionDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "local_wshelper_add_section");
        params.add("moodlewsrestformat", "json");

        params.add("courseid", courseSectionDTO.getCourseid().toString());
        params.add("section", courseSectionDTO.getSection().toString());
        params.add("name", courseSectionDTO.getName());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());

        try {
            // Moodle trả về một mảng JSON, ta hứng bằng một mảng các đối tượng Response
            ResponseEntity<ResponseCourseSectionDTO> response = restTemplate.exchange(MOODLE_URL, HttpMethod.POST, request, ResponseCourseSectionDTO.class);

            ResponseCourseSectionDTO responseBody = response.getBody();
            if (responseBody != null) {
                return responseBody.getId();
            }
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ tạo mới sang Moodle: " + e.getMessage());
        }
        return null;
    }

    public void updateMoodleCourseSection(CourseSectionDTO courseSectionDTO, Long moodleCourseSectionId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "local_wshelper_update_section");
        params.add("moodlewsrestformat", "json");

        params.add("sectionid", String.valueOf(moodleCourseSectionId));
        params.add("name", courseSectionDTO.getName());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());
        try {
            restTemplate.exchange(MOODLE_URL, HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ cập nhật sang Moodle: " + e.getMessage());
        }
    }

    public boolean deleteMoodleCourseSection(Long moodleCourseSectionId){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "local_wshelper_delete_section");
        params.add("moodlewsrestformat", "json");
        params.add("sectionid", String.valueOf(moodleCourseSectionId));

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
