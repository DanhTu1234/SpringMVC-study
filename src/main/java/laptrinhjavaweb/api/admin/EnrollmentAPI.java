package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.service.IErollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "classEnrollmentAPIOfAdmin")
@CrossOrigin("http://127.0.0.1:5500")
public class EnrollmentAPI {
    @Autowired
    private IErollmentService EnrollmentService;

    @PostMapping("/api/sync/enrollments")
    public ResponseEntity<String> syncAllEnrollments() {
        EnrollmentService.syncAllEnrollments();
        return ResponseEntity.ok("Đồng bộ tất cả enrollment thành công!");
    }
}
