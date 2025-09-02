package laptrinhjavaweb.service;

import laptrinhjavaweb.model.SemesterModel;

import java.util.List;

public interface ISemesterService {
    List<SemesterModel> findAll();
}
