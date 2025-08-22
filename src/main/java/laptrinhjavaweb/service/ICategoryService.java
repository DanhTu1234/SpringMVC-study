package laptrinhjavaweb.service;

import laptrinhjavaweb.model.CategoryNewModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryNewModel> findAll();
}
