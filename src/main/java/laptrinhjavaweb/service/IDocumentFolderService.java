package laptrinhjavaweb.service;

import laptrinhjavaweb.model.DocumentFolderModel;

import java.util.List;

public interface IDocumentFolderService {
    DocumentFolderModel insert(DocumentFolderModel folderModel);
    List<DocumentFolderModel> getFolderTree();
}
