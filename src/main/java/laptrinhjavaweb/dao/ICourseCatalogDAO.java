package laptrinhjavaweb.dao;


import laptrinhjavaweb.model.Course_CatalogModel;

import java.util.List;

public interface ICourseCatalogDAO {
    List<Course_CatalogModel> findAll();
}
