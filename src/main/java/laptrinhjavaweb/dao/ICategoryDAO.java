package laptrinhjavaweb.dao;

import laptrinhjavaweb.dto.CourseCategoryDTO;
import laptrinhjavaweb.model.CategoryCourseModel;

import java.util.List;

public interface ICategoryDAO{
    CategoryCourseModel insert(CategoryCourseModel categoryCourseModel);
    CategoryCourseModel update(CategoryCourseModel updateCategoryCourse);
    void delete(long id);
    List<CategoryCourseModel> findAll();
    CategoryCourseModel findOne(long id);
    CourseCategoryDTO findCategoryById(Long id);
}
