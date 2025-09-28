package laptrinhjavaweb.service;

import laptrinhjavaweb.model.CategoryCourseModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryCourseModel> findAll();
    CategoryCourseModel createSyncCategory(CategoryCourseModel categoryCourseModel);
    CategoryCourseModel updateSyncCategory(CategoryCourseModel categoryCourseModel);
    void deleteSyncCategory(Long id);
    CategoryCourseModel insert(CategoryCourseModel categoryCourseModel);
    CategoryCourseModel update(CategoryCourseModel updateCategory);
    void delete(long id);
    CategoryCourseModel findOne(Long id);
}
