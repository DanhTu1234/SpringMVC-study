package laptrinhjavaweb.service;

import laptrinhjavaweb.model.DocumentModel;
import org.springframework.web.multipart.MultipartFile;

public interface IDocumentService {
    DocumentModel uploadFile(MultipartFile file, Long folderId);
}
