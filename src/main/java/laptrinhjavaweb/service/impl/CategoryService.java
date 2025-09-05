package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.client.SyncServiceCourseCategory;
import laptrinhjavaweb.dao.ICategoryDAO;
import laptrinhjavaweb.dto.CourseCategoryDTO;
import laptrinhjavaweb.dto.CourseDTO;
import laptrinhjavaweb.model.CategoryCourseModel;
import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDAO categoryDao;

    @Autowired
    private SyncServiceCourseCategory syncServiceCourseCategory;

    @Override
    public List<CategoryCourseModel> findAll() {

        return categoryDao.findAll();
    }

    @Override
    public CategoryCourseModel insert(CategoryCourseModel categoryCourseModel) {
        return categoryDao.insert(categoryCourseModel);
    }

    @Override
    public CategoryCourseModel update(CategoryCourseModel updateCategory) {
        categoryDao.update(updateCategory);
        return categoryDao.findOne(updateCategory.getId());
    }

    @Override
    public void delete(long id) {
        categoryDao.delete(id);
    }

    @Override
    @Transactional
    public CategoryCourseModel createSyncCategory(CategoryCourseModel categoryCourseModel) {
        CategoryCourseModel save = categoryDao.insert(categoryCourseModel);

        CourseCategoryDTO dtoToSend = categoryDao.findCategoryById(save.getId());

        Long lmsId = syncServiceCourseCategory.createMoodleCourseCategory(dtoToSend);
        if (lmsId != null) {
            save.setLms_category_id(lmsId);
            categoryDao.update(save);
        }else {
            throw new RuntimeException("Đồng bộ sang LMS thất bại!");
        }
        return save;
    }

    @Override
    @Transactional
    public CategoryCourseModel updateSyncCategory(CategoryCourseModel categoryCourseModel){
        CategoryCourseModel updateLcms =  categoryDao.update(categoryCourseModel);

        Long  lmsId = updateLcms.getLms_category_id();
        if (lmsId != null) {
            CourseCategoryDTO dtoToSend = categoryDao.findCategoryById(updateLcms.getId());
            syncServiceCourseCategory.updateMoodleCourseCategory(dtoToSend,lmsId);
        }else {
            throw new RuntimeException("Không tìm thấy");
        }
        return updateLcms;
    }

    @Override
    @Transactional
    public void deleteSyncCategory(Long id){
        CategoryCourseModel delete = categoryDao.findOne(id);
        if (delete == null) {
            throw new RuntimeException("Không tìm thấy lớp học phần để xóa.");
        }
        Long lmsId = delete.getLms_category_id();
        if (lmsId != null) {
            boolean isDeletedOnLms = syncServiceCourseCategory.deleteMoodleCourseCategory(lmsId);
            if (!isDeletedOnLms) {
                throw new RuntimeException("Xóa đồng bộ sang LMS thất bại!");
            }
        }
        categoryDao.delete(id);
    }

}
