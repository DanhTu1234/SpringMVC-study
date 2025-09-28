package laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import laptrinhjavaweb.client.SyncServiceCourse;
import laptrinhjavaweb.dao.ICategoryDAO;
import laptrinhjavaweb.dao.ICourseDAO;

import laptrinhjavaweb.dto.CourseDTO;
import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseDAO courseDao;

    @Autowired
    private SyncServiceCourse syncServiceCourse;

    @Override
    public List<CourseModel> findAll() {
        return courseDao.findAll();
    }

    @Override
    @Transactional
    public CourseModel createSyncCourse(CourseModel courseModel) {
        CourseModel save = courseDao.insert(courseModel);

        CourseDTO dtoToSend = courseDao.findCourseById(save.getId());

        Long lmsId = syncServiceCourse.createMoodleCourse(dtoToSend);
        if (lmsId != null) {
            save.setLmsCourseId(lmsId);
            courseDao.update(save);
        }else {
            throw new RuntimeException("Đồng bộ sang LMS thất bại!");
        }

        return save;
    }

    @Override
    @Transactional
    public CourseModel updateSyncCourse(CourseModel courseModel){
        CourseModel updateLcms =  courseDao.update(courseModel);

        Long lmsId = updateLcms.getLmsCourseId();
        if (lmsId != null) {
            CourseDTO dtoToSend = courseDao.findCourseById(updateLcms.getId());
            syncServiceCourse.updateMoodleCourse(dtoToSend,lmsId);
        }else {
            throw new RuntimeException("Không tìm thấy lms_offering_id");
        }

        return updateLcms;
    }

    @Override
    @Transactional
    public void deleteSyncCourse(Long id){
        CourseModel delete = courseDao.findOne(id);
        if (delete == null) {
            throw new RuntimeException("Không tìm thấy lớp học phần để xóa.");
        }
        Long lmsId = delete.getLmsCourseId();
        if (lmsId != null) {
            // GỌI API ĐỂ XÓA BẢN GHI BÊN LMS TRƯỚC
            boolean isDeletedOnLms = syncServiceCourse.deleteMoodleCourse(lmsId);
            if (!isDeletedOnLms) {
                throw new RuntimeException("Xóa đồng bộ sang LMS thất bại!");
            }
        }
        courseDao.delete(id);
    }

    @Override
    public CourseModel insert(CourseModel courseModel) {
        courseModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        return courseDao.insert(courseModel);
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
        //CourseModel courseModel = courseDao.findOne(id);
        return courseDao.findOne(id);
    }

}