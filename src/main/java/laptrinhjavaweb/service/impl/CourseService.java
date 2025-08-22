package laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import laptrinhjavaweb.dao.ICategoryDAO;
import laptrinhjavaweb.dao.ICourseDAO;

import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseDAO courseDao;

    @Autowired
    private ICategoryDAO categoryDao;

    @Override
    public List<CourseModel> findAll() {
        return courseDao.findAll();
    }

    @Override
    public CourseModel insert(CourseModel courseModel) {
        courseModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        Long newId = courseDao.insert(courseModel);
        return courseDao.findOne(newId);
    }

    @Override
    public CourseModel update(CourseModel updateCourse) {
        // Lấy dữ liệu cũ từ DB
//        CourseModel oldNew = courseDao.findOne(updateCourse.getId());
//        // Giữ lại các thông tin không thay đổi
//        updateCourse.setCreatedDate(oldNew.getCreatedDate());
//        updateCourse.setCreatedBy(oldNew.getCreatedBy());
//        updateCourse.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//        CategoryNewModel category = categoryDao.findOneByCode(updateNew.getCategoryCode());
//        updateNew.setCategoryId(category.getId());
        // Gọi DAO để cập nhật vào DB
        courseDao.update(updateCourse);
        // Lấy lại dữ liệu đã cập nhật để trả về
        return courseDao.findOne(updateCourse.getId());
    }

    @Override
    public void delete(long id) {
        courseDao.delete(id);
    }

    @Override
    public CourseModel findOne(Long id) {
        CourseModel courseModel = courseDao.findOne(id);
//        CategoryNewModel categoryNewModel = categoryDao.findOne(newModel.getCategoryId());
//        newModel.setCategoryCode(categoryNewModel.getCode());
        return courseModel;
    }

}