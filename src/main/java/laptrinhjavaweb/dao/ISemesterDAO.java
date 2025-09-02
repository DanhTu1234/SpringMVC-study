package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.DepartmentModel;
import laptrinhjavaweb.model.SemesterModel;

import java.util.List;

public interface ISemesterDAO {
    List<SemesterModel> findAll();
}
