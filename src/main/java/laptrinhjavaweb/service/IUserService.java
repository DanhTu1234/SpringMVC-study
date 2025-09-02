package laptrinhjavaweb.service;

import laptrinhjavaweb.model.UserModel;

import java.util.List;

public interface IUserService {
    List<UserModel> findAll();
}
