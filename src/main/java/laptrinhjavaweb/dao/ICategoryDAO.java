package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.CategoryCourseModel;

import java.util.List;

public interface ICategoryDAO{
    List<CategoryCourseModel> findAll();
    CategoryCourseModel findOne(long id);
    CategoryCourseModel findOneByCode(String code);
}
