package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.DocumentFolderModel;

import java.util.List;

public interface IDocumentFolderDAO {
    List<DocumentFolderModel> findAll();
    DocumentFolderModel insert(DocumentFolderModel folderModel);
}
