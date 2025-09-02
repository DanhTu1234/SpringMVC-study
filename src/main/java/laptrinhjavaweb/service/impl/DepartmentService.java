package laptrinhjavaweb.service.impl;


import laptrinhjavaweb.dao.impl.DepartmentDAO;
import laptrinhjavaweb.model.DepartmentModel;
import laptrinhjavaweb.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public List<DepartmentModel> findAll() {
        return departmentDAO.findAll();
    }

    @Override
    public DepartmentModel findOne(Long id) {
        DepartmentModel departmentModel = departmentDAO.findOne(id);
        return departmentModel;
    }

    @Override
    public DepartmentModel insert(DepartmentModel departmentModel) {
        Long newId = departmentDAO.insert(departmentModel);
        return departmentDAO.findOne(newId);
    }

    @Override
    public DepartmentModel update(DepartmentModel updateDepartment) {
        departmentDAO.update(updateDepartment);
        return departmentDAO.findOne(updateDepartment.getId());
    }

    @Override
    public void delete(long id) {
        departmentDAO.delete(id);
    }
}
