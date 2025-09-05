package laptrinhjavaweb.dao;

import laptrinhjavaweb.dto.CourseDTO;
import laptrinhjavaweb.model.CourseModel;

import java.util.List;

public interface ICourseDAO {
    List<CourseModel> findAll();
    CourseDTO findCourseById(long id);
    CourseModel insert(CourseModel courseModel);
    CourseModel update(CourseModel updateCourse);
    void delete(long id);
    CourseModel findOne(Long id);
}
