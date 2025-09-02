package laptrinhjavaweb.service;

import laptrinhjavaweb.model.Course_CatalogModel;
import laptrinhjavaweb.model.DepartmentModel;

import java.util.List;

public interface ICourseCatalogService {
    List<Course_CatalogModel> findAll();
}
