package laptrinhjavaweb.client;

import laptrinhjavaweb.dto.Class_OfferingDTO;
import laptrinhjavaweb.dto.ResponseClassOfferingDTO;
import laptrinhjavaweb.model.Class_OfferingModel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SyncServiceOffering {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String LMS_URL =  "http://localhost:8081/Test-api/api/offerings";

    public Long createLmsOffering(Class_OfferingDTO class_OfferingDTO) {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        // Tạo một request entity chứa đối tượng Class_OfferingModel và header
        HttpEntity<Class_OfferingDTO> request = new HttpEntity<>(class_OfferingDTO, header);

        try {
            // Gọi API POST của LMS qua exchange
            ResponseEntity<ResponseClassOfferingDTO> response = restTemplate.exchange(LMS_URL, HttpMethod.POST, request, ResponseClassOfferingDTO.class);

            // Lấy body Class_OfferingModel từ response
            ResponseClassOfferingDTO responseFromLMS = response.getBody();
            if (responseFromLMS != null) {
                return responseFromLMS.getOfferingId();
            }

        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ sang LMS: " + e.getMessage());
        }
        // Trả về null nếu có lỗi
        return null;
    }

    public void updateLmsOffering(Class_OfferingDTO class_OfferingDTO, Long lmsId) {
        String updateUrl = LMS_URL + "/" + lmsId;
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Class_OfferingDTO> request = new HttpEntity<>(class_OfferingDTO, header);

        try {
            restTemplate.exchange(updateUrl, HttpMethod.PUT, request, Class_OfferingDTO.class);
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ sang LMS: " + e.getMessage());
        }
    }

    public boolean deleteLmsOffering(Long lmsId) {
        String deleteUrl = LMS_URL + "/" + lmsId;
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> request = new HttpEntity<>(header);

        try {
            restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, Void.class);
            return true;
        } catch (Exception e) {
            System.err.println(">>> Lỗi khi đồng bộ sang LMS: " + e.getMessage());
            return false;
        }
    }
}
