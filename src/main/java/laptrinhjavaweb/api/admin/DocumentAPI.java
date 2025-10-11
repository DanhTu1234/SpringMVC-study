package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.DocumentModel;
import laptrinhjavaweb.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController(value = "documentAPIOfAdmin")
@CrossOrigin("http://127.0.0.1:5500")
public class DocumentAPI {
    @Autowired
    private IDocumentService documentService;

    @GetMapping("/api/documents")
    public ResponseEntity<List<DocumentModel>> getAllDocuments() {
        List<DocumentModel> document = documentService.findAll();
        return ResponseEntity.ok(document);
    }

    @GetMapping("/api/documents/{fileName}/preview")
    public ResponseEntity<String> previewFile(@PathVariable String fileName) {
        String url = documentService.generatePresignedUrl(fileName, false);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/api/documents/{fileName}/download")
    public ResponseEntity<String> downloadFile(@PathVariable String fileName) {
        try {
            String url = documentService.generatePresignedUrl(fileName, true);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Không thể tạo link tải file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/documents")
    public ResponseEntity<DocumentModel> uploadDocument(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) { //Nếu file request rỗng
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            DocumentModel saveDocument = documentService.uploadFile(file);
            return new ResponseEntity<>(saveDocument, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/documents/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        documentService.delete(id);
        return ResponseEntity.ok().build();
    }
}