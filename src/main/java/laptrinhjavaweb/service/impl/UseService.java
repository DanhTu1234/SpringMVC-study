package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.dao.ISemesterDAO;
import laptrinhjavaweb.dao.IUserDAO;
import laptrinhjavaweb.model.UserModel;
import laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UseService implements IUserService {
    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<UserModel> findAll() {
        return userDAO.findAll();
    }
}
