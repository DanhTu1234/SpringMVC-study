package laptrinhjavaweb.service;

import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.model.CourseSectionModel;

import java.util.List;

public interface ICourseSectionService {
    List<CourseSectionModel> findAll();
    CourseSectionModel insert(CourseSectionModel courseSectionModel);
    CourseSectionModel update(CourseSectionModel courseSectionModel);
    void delete(long id);
    CourseSectionModel createSyncCourseSection(CourseSectionModel courseSectionModel);
    CourseSectionModel updateSyncCourseSection(CourseSectionModel courseSectionModel);
    void deleteSyncCourseSection(Long id);
}
