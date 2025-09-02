package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.IClass_OfferingDAO;
import laptrinhjavaweb.dao.ICourseCatalogDAO;
import laptrinhjavaweb.model.Course_CatalogModel;
import laptrinhjavaweb.service.ICourseCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseCatalogService implements ICourseCatalogService {
    @Autowired
    private ICourseCatalogDAO courseCatalogDAO;

    @Override
    public List<Course_CatalogModel> findAll() {
        return courseCatalogDAO.findAll();
    }
}
