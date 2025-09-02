package laptrinhjavaweb.service;

import laptrinhjavaweb.model.DepartmentModel;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentModel> findAll();
    DepartmentModel findOne(Long id);
    DepartmentModel insert(DepartmentModel departmentModel);
    DepartmentModel update(DepartmentModel updateDepartment);
    void delete(long id);
}
