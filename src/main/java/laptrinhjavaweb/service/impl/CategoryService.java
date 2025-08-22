package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.ICategoryDAO;
import laptrinhjavaweb.model.CategoryNewModel;
import laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDAO categoryDao;

    @Override
    public List<CategoryNewModel> findAll() {

        return categoryDao.findAll();
    }
}
