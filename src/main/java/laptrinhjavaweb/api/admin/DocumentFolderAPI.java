package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.DocumentFolderModel;
import laptrinhjavaweb.model.LOVersionModel;
import laptrinhjavaweb.model.LearningObjectModel;
import laptrinhjavaweb.service.IDocumentFolderService;
import laptrinhjavaweb.service.ILeaningObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "documentFolderAPIOfAdmin")
@CrossOrigin("http://127.0.0.1:5500")
public class DocumentFolderAPI {
    @Autowired
    private IDocumentFolderService documentFolderService;

    @Autowired
    private ILeaningObjectService leaningObjectService;

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

    @GetMapping("/api/folders/learningObjects/{folderId}" )
    public ResponseEntity<List<LearningObjectModel>> getLOByFolderId(@PathVariable Long folderId) {
        List<LearningObjectModel> learningObjects = leaningObjectService.getLearningObjectByFolder(folderId);
        return ResponseEntity.ok(learningObjects);
    }

    @GetMapping("/api/learningObjects/{loId}" )
    public ResponseEntity<List<LOVersionModel>> getLOVersionsByLOId(@PathVariable int loId) {
        List<LOVersionModel> learningObjects = leaningObjectService.getLOVersionsByLOId(loId);
        return ResponseEntity.ok(learningObjects);
    }
}
