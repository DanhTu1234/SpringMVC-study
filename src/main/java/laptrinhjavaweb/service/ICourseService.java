package laptrinhjavaweb.service;

import laptrinhjavaweb.model.CourseModel;

import java.util.List;

public interface ICourseService {
    List<CourseModel> findAll();
    CourseModel insert(CourseModel courseModel);
    CourseModel update(CourseModel updateCourse);
    void delete(long id);
    CourseModel findOne(Long id);
}
