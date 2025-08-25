package laptrinhjavaweb.client;

import laptrinhjavaweb.model.CourseModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SyncService {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String LMS_URL = "http://localhost:8081/Test-api/api/courses";
    public CourseModel pushLMS(CourseModel courseModel) {
        // Thiết lập header để báo cho server biết chúng ta gửi dữ liệu JSON
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        // Tạo một request entity chứa đối tượng CourseModel và header
        HttpEntity<CourseModel> request = new HttpEntity<>(courseModel, header);

        try {
            // Gọi API POST của LMS qua exchange
            ResponseEntity<CourseModel> response = restTemplate.exchange(LMS_URL, HttpMethod.POST, request, CourseModel.class);

            // Lấy body CourseModel từ response
            CourseModel responseFromLMS = response.getBody();

            // Kiểm tra và trả về kết quả
            if (responseFromLMS != null) {
                return responseFromLMS;
            }
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ sang LMS: " + e.getMessage());
        }

        // Trả về null nếu có lỗi
        return null;
    }

    public void updateLMS(CourseModel courseModel) {
        String updateUrl = LMS_URL + "/{id}";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CourseModel> request = new HttpEntity<>(courseModel, header);

        try {
            restTemplate.exchange(updateUrl, HttpMethod.PUT, request, CourseModel.class, courseModel.getLmsCourseId());
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ sang LMS: " + e.getMessage());
        }
    }

    public void deleteLMS(CourseModel courseModel) {
        String deleteUrl = LMS_URL + "/{id}";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> request = new HttpEntity<>(header);

        try {
            restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, Void.class, courseModel.getLmsCourseId());
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ sang LMS: " + e.getMessage());
        }
    }

}

//@Service
//public class SyncService {
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    private static final String MOODLE_URL = "http://localhost/moodle/webservice/rest/server.php";
//    private static final String TOKEN = "ffcb0a28c3613061e97b8110bb9faa9c";
//
//    public Long pushToMoodle(CourseModel courseModel) {
//        String apiUrl = MOODLE_URL
//                + "?wstoken=" + TOKEN
//                + "&moodlewsrestformat=json"
//                + "&wsfunction=core_course_create_courses";
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("courses[0][fullname]", courseModel.getFullName());
//        params.add("courses[0][shortname]", courseModel.getShortName());
//        params.add("courses[0][summary]", courseModel.getSummary());
//        params.add("courses[0][categoryid]", "1"); // category id phải tồn tại trong Moodle
//
//        try {
//            List<Map<String, Object>> response =
//                    restTemplate.postForObject(apiUrl, params, List.class);
//
//            if (response != null && !response.isEmpty()) {
//                Integer moodleId = (Integer) response.get(0).get("id");
//                System.out.println(">>> Tạo khóa học trên Moodle thành công, id = " + moodleId);
//                return moodleId.longValue();
//            }
//        } catch (Exception e) {
//            System.out.println(">>> Lỗi khi gửi sang Moodle: " + e.getMessage());
//        }
//        return null;
//    }
//}
