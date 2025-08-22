package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.CategoryNewModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO {
    List<CategoryNewModel> findAll();
    CategoryNewModel findOne(long id);
    CategoryNewModel findOneByCode(String code);
}
