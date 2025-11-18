package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.service.IDocumentService;
import laptrinhjavaweb.service.ILeaningObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/learning-objects")
@CrossOrigin("http://127.0.0.1:5500")
public class LeaningObjectAPI {
    @Autowired
    private ILeaningObjectService leaningObjectService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadLearningObject(
            @RequestParam("folderId") Long folderId,
            @RequestParam("title") String title,
            @RequestParam("version") String version,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            leaningObjectService.uploadLearningObject(folderId, title, version, description, file);
            return ResponseEntity.ok("Upload successful!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @PostMapping("/uploadVersion/{loId}")
    public ResponseEntity<String> uploadNewVersion(
            @PathVariable("loId") int loId,
            @RequestParam("version") String version,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            leaningObjectService.uploadNewVersion(loId, version, file);
            return ResponseEntity.ok("Upload new version successful!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/preview/{fileName}")
    public ResponseEntity<String> previewFile(@PathVariable String fileName) {
        String url = leaningObjectService.generatePresignedUrl(fileName, false);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<String> downloadFile(@PathVariable String fileName) {
        try {
            String url = leaningObjectService.generatePresignedUrl(fileName, true);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Không thể tạo link tải file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
