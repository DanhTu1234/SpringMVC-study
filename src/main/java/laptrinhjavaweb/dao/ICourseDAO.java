package laptrinhjavaweb.dao;

import laptrinhjavaweb.model.CourseModel;

import java.util.List;

public interface ICourseDAO {
    List<CourseModel> findAll();
    Long insert(CourseModel courseModel);
    void update(CourseModel updateCourse);
    void delete(long id);
    CourseModel findOne(Long id);
}
