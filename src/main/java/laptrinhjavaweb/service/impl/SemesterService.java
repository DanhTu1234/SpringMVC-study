package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.impl.DepartmentDAO;
import laptrinhjavaweb.dao.impl.SemesterDAO;
import laptrinhjavaweb.model.SemesterModel;
import laptrinhjavaweb.service.ISemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SemesterService implements ISemesterService {
    @Autowired
    private SemesterDAO semesterDAO;

    @Override
    public List<SemesterModel> findAll() {
        return semesterDAO.findAll();
    }
}
