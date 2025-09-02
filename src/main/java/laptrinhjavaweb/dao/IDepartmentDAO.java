package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.model.DepartmentModel;

import java.util.List;

public interface IDepartmentDAO {
    List<DepartmentModel> findAll();
    DepartmentModel findOne(Long id);
    Long insert(DepartmentModel departmentModel);
    void update(DepartmentModel updateDepartment);
    void delete(long id);
}
