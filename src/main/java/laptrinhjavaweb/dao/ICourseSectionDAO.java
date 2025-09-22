package laptrinhjavaweb.dao;

import laptrinhjavaweb.dto.CourseSectionDTO;
import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.model.CourseSectionModel;

import java.util.List;

public interface ICourseSectionDAO {
    List<CourseSectionModel> findAll();
    CourseSectionModel insert(CourseSectionModel courseSectionModel);
    CourseSectionModel update(CourseSectionModel courseSectionModel);
    void delete(long id);
    CourseSectionDTO findById(long id);
    CourseSectionModel findOne(Long id);
    CourseSectionModel updateName(CourseSectionModel courseSectionModel);
}
