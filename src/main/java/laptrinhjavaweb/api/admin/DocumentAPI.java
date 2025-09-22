package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.DocumentModel;
import laptrinhjavaweb.service.IDocumentFolderService;
import laptrinhjavaweb.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController(value = "documentAPIOfAdmin")
public class DocumentAPI {
    @Autowired
    private IDocumentService documentService;

    @PostMapping("/api/folders/{folderId}/documents")
    public ResponseEntity<DocumentModel> uploadDocument(@PathVariable Long folderId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) { //Nếu file request rỗng
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            DocumentModel saveDocument = documentService.uploadFile(file, folderId);
            return new ResponseEntity<>(saveDocument, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
