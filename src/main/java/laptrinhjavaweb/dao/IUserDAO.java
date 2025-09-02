package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.SemesterModel;
import laptrinhjavaweb.model.UserModel;

import java.util.List;

public interface IUserDAO {
    List<UserModel> findAll();
}
