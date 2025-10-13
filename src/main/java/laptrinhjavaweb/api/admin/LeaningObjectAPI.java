package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.service.IDocumentService;
import laptrinhjavaweb.service.ILeaningObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@RestController
@CrossOrigin("http://127.0.0.1:5501")
public class LeaningObjectAPI {
    @Autowired
    private ILeaningObjectService leaningObjectService;

    @PostMapping("/api/learning-objects/upload")
    public ResponseEntity<String> uploadLearningObject(
            @RequestParam("title") String title,
            @RequestParam("version") String version,
            @RequestParam("description") String description,
            @RequestParam("createdAt") Timestamp createdAt,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            leaningObjectService.uploadLearningObject(title, version, description, createdAt, file);
            return ResponseEntity.ok("Upload successful!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }
}
