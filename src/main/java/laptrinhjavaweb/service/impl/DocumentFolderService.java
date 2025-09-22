package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.IDocumentFolderDAO;
import laptrinhjavaweb.model.DocumentFolderModel;
import laptrinhjavaweb.service.IDocumentFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocumentFolderService implements IDocumentFolderService {
    @Autowired
    private IDocumentFolderDAO documentFolderDAO;

    @Override
    public DocumentFolderModel insert(DocumentFolderModel folderModel) {
        return documentFolderDAO.insert(folderModel);
    }

    @Override
    public List<DocumentFolderModel> getFolderTree() {
        // Lấy danh sách tất cả các thư mục từ CSDL
        List<DocumentFolderModel> allFolders = documentFolderDAO.findAll();

        // Dùng Map để truy cập nhanh các thư mục theo ID
        Map<Long, DocumentFolderModel> folderMap = new HashMap<>();
        for (DocumentFolderModel folder : allFolders) {
            folderMap.put(folder.getId(), folder);
        }

        // Xây dựng cây
        List<DocumentFolderModel> tree = new ArrayList<>();
        for (DocumentFolderModel folder : allFolders) {
            if (folder.getParentId() == null) {
                // Nếu là thư mục gốc, thêm vào cây
                tree.add(folder);
            } else {
                // Nếu là thư mục con, tìm cha của nó và thêm vào danh sách con của cha
                DocumentFolderModel parent = folderMap.get(folder.getParentId());
                if (parent != null) {
                    parent.getChildren().add(folder);
                }
            }
        }
        return tree;
    }
}
