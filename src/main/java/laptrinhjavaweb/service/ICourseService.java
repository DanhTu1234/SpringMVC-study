package laptrinhjavaweb.service;

import laptrinhjavaweb.model.CourseModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICourseService {
    List<CourseModel> findAll();
    CourseModel createSyncCourse(CourseModel courseModel);
    CourseModel updateSyncCourse(CourseModel courseModel);
    void deleteSyncCourse(Long id);
    CourseModel insert(CourseModel courseModel);
    CourseModel update(CourseModel updateCourse);
    void delete(long id);
    CourseModel findOne(Long id);
}
