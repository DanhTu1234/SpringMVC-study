package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.client.SyncServiceCourseSection;
import laptrinhjavaweb.dao.ICourseSectionDAO;
import laptrinhjavaweb.dto.CourseDTO;
import laptrinhjavaweb.dto.CourseSectionDTO;
import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.model.CourseSectionModel;
import laptrinhjavaweb.service.ICourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class CourseSectionService implements ICourseSectionService {
    @Autowired
    private ICourseSectionDAO courseSectionDao;

    @Autowired
    private SyncServiceCourseSection syncServiceCourseSection;

    @Override
    public List<CourseSectionModel> findAll() {
        return courseSectionDao.findAll();
    }

    @Override
    public CourseSectionModel insert(CourseSectionModel courseSectionModel) {
        return courseSectionDao.insert(courseSectionModel);
    }

    @Override
    public CourseSectionModel update(CourseSectionModel courseSectionModel) {
        return courseSectionDao.update(courseSectionModel);
    }

    @Override
    public void delete(long id) {
        courseSectionDao.delete(id);
    }

    @Override
    @Transactional
    public CourseSectionModel createSyncCourseSection(CourseSectionModel courseSectionModel) {
        CourseSectionModel save = courseSectionDao.insert(courseSectionModel);

        CourseSectionDTO dtoToSend = courseSectionDao.findById(save.getId());

        Long lmsId = syncServiceCourseSection.createMoodleCourseSection(dtoToSend);
        if (lmsId != null) {
            save.setLmsSectionId(lmsId);
            courseSectionDao.update(save);
        }else {
            throw new RuntimeException("Đồng bộ sang LMS thất bại!");
        }

        return save;
    }

    @Override
    @Transactional
    public CourseSectionModel updateSyncCourseSection(CourseSectionModel courseSectionModel){
        CourseSectionModel updateLcms =  courseSectionDao.updateName(courseSectionModel);

        Long lmsId = updateLcms.getLmsSectionId();
        if (lmsId != null) {
            CourseSectionDTO dtoToSend = courseSectionDao.findById(updateLcms.getId());
            syncServiceCourseSection.updateMoodleCourseSection(dtoToSend,lmsId);
        }else {
            throw new RuntimeException("Không tìm thấy lms_offering_id");
        }

        return updateLcms;
    }

    @Override
    @Transactional
    public void deleteSyncCourseSection(Long id){
        CourseSectionModel delete = courseSectionDao.findOne(id);
        if (delete == null) {
            throw new RuntimeException("Không tìm thấy section để xóa");
        }
        Long lmsId = delete.getLmsSectionId();
        if (lmsId != null) {
            // GỌI API ĐỂ XÓA BẢN GHI BÊN LMS TRƯỚC
            boolean isDeletedOnLms = syncServiceCourseSection.deleteMoodleCourseSection(lmsId);
            if (!isDeletedOnLms) {
                throw new RuntimeException("Xóa đồng bộ sang LMS thất bại!");
            }
        }
        courseSectionDao.delete(id);
    }
}
