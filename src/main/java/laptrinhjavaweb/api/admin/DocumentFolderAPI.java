package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.DocumentFolderModel;
import laptrinhjavaweb.service.IDocumentFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "documentFolderAPIOfAdmin")
public class DocumentFolderAPI {
    @Autowired
    private IDocumentFolderService documentFolderService;

    @PostMapping("/api/folders")
    public ResponseEntity<DocumentFolderModel> createFolder(@RequestBody DocumentFolderModel folderModel) {
        DocumentFolderModel savedFolder = documentFolderService.insert(folderModel);
        return new ResponseEntity<>(savedFolder, HttpStatus.CREATED);
    }

    @GetMapping("/api/folders")
    public ResponseEntity<List<DocumentFolderModel>> getFolderTree() {
        List<DocumentFolderModel> folderTree = documentFolderService.getFolderTree();
        return new ResponseEntity<>(folderTree, HttpStatus.OK);
    }
}
