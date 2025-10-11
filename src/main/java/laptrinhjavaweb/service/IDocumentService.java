package laptrinhjavaweb.service;

import laptrinhjavaweb.model.DocumentModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDocumentService {
    List<DocumentModel> findAll();
    DocumentModel uploadFile(MultipartFile file);
    String generatePresignedUrl(String fileName,  boolean download);
    void delete(Long id);
}
