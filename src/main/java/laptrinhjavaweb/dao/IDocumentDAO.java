package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.DocumentModel;

import java.util.List;

public interface IDocumentDAO {
    List<DocumentModel> findAll();
    DocumentModel insert(DocumentModel documentModel);
    void delete(Long id);
}
