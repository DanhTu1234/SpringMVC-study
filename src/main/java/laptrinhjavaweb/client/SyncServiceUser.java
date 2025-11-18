package laptrinhjavaweb.client;

import laptrinhjavaweb.dto.ResponseCourseDTO;
import laptrinhjavaweb.dto.ResponseEnrollUserDTO;
import laptrinhjavaweb.dto.ResponseUserDTO;
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
public class SyncServiceUser {
    private final RestTemplate restTemplate = new RestTemplate();

    private String MOODLE_URL = "http://localhost/moodleLms/webservice/rest/server.php";

    private String MOODLE_TOKEN = "8ddb2d81f5adebc934803e08373d9b41";

    public List<ResponseUserDTO> getAllMoodleUsers() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "local_wshelper_get_all_user");
        params.add("moodlewsrestformat", "json");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());
        try {
            ResponseEntity<ResponseUserDTO[]> response = restTemplate.exchange(
                    MOODLE_URL,
                    HttpMethod.POST,
                    request,
                    ResponseUserDTO[].class
            );
            ResponseUserDTO[] responseBody = response.getBody();
            if (responseBody != null) {
                // Chuyển mảng thành một List
                return Arrays.asList(responseBody);
            }
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi lấy danh sách người dùng từ Moodle: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<ResponseEnrollUserDTO> getEnrollUserInCourse(Long id){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("wstoken", MOODLE_TOKEN);
        params.add("wsfunction", "core_enrol_get_enrolled_users");
        params.add("moodlewsrestformat", "json");
        params.add("courseid", String.valueOf(id));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, new HttpHeaders());
        try {
            ResponseEntity<ResponseEnrollUserDTO[]> response = restTemplate.exchange(
                    MOODLE_URL,
                    HttpMethod.POST,
                    request,
                    ResponseEnrollUserDTO[].class
            );
            ResponseEnrollUserDTO[] responseBody = response.getBody();
            if (responseBody != null) {
                return Arrays.asList(responseBody);
            }
        }catch (Exception e){
            System.err.println("Lỗi khi lấy danh sách người dùng đã ghi danh trong khóa học từ Moodle: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
