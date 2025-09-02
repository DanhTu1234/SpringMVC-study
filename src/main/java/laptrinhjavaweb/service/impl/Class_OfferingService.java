package laptrinhjavaweb.service.impl;

import laptrinhjavaweb.client.SyncServiceOffering;
import laptrinhjavaweb.dao.IClass_OfferingDAO;
import laptrinhjavaweb.dto.Class_OfferingDTO;
import laptrinhjavaweb.model.Class_OfferingModel;
import laptrinhjavaweb.service.IClass_OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Class_OfferingService implements IClass_OfferingService {
    @Autowired
    private IClass_OfferingDAO class_OfferingDAO;

    @Autowired
    private SyncServiceOffering syncServiceOffering;

    @Override
    public List<Class_OfferingModel> findAll() {
        return class_OfferingDAO.findAll();
    }

    @Override
    public Class_OfferingModel insert(Class_OfferingModel class_OfferingModel) {
        return class_OfferingDAO.insert(class_OfferingModel);
    }

    @Override
    public Class_OfferingModel update(Class_OfferingModel updateClass_Offering) {
        class_OfferingDAO.update(updateClass_Offering);
        return class_OfferingDAO.findOne(updateClass_Offering.getId());
    }

    @Override
    public void delete(long id) {
        class_OfferingDAO.delete(id);
    }

    @Override
    public Class_OfferingModel findOne(Long id) {
        return class_OfferingDAO.findOne(id);
    }

    @Override
    public Class_OfferingDTO findDetailById(Long id) {
        return class_OfferingDAO.findDetailById(id);
    }

    @Override
    @Transactional
    public Class_OfferingModel createAndSyncOffering(Class_OfferingModel offeringModel) {
        // Lưu vào Lcms
        Class_OfferingModel savedInLCMS = class_OfferingDAO.insert(offeringModel);

        // Dịch dữ liệu từ Id sang Tên
        Class_OfferingDTO dtoToSend = class_OfferingDAO.findDetailById(savedInLCMS.getId());

        // Gọi Api sang Lms
        Long lmsId = syncServiceOffering.createLmsOffering(dtoToSend);

        // Cập nhật Id mới tạo bên Lms sang Lcms
        if (lmsId != null) {
            savedInLCMS.setLms_Offering_Id(lmsId);
            class_OfferingDAO.update(savedInLCMS);
        } else {
            throw new RuntimeException("Đồng bộ sang LMS thất bại!");
        }

        return savedInLCMS;
    }

    @Override
    @Transactional
    public Class_OfferingModel updateAndSyncOffering(Class_OfferingModel offeringModel) {
        // CẬP NHẬT VÀO CSDL CỦA LCMS
        Class_OfferingModel updatedInLCMS = class_OfferingDAO.update(offeringModel);

        // LẤY ID THAM CHIẾU CỦA LMS
        Long lmsId = updatedInLCMS.getLms_Offering_Id();

        if (lmsId != null) {
            // Chuyển DỮ LIỆU SANG DẠNG TÊN
            Class_OfferingDTO dtoToSend = class_OfferingDAO.findDetailById(updatedInLCMS.getId());

            // GỌI API ĐỒNG BỘ
            syncServiceOffering.updateLmsOffering(dtoToSend, lmsId);
        } else {
            throw new RuntimeException("Không tìm thấy lms_offering_id");
        }

        return updatedInLCMS;
    }

    @Override
    @Transactional
    public void deleteAndSyncOffering(long id) {
        // LẤY ID THAM CHIẾU CỦA LMS TRƯỚC KHI XÓA
        Class_OfferingModel offeringToDelete = class_OfferingDAO.findOne(id);
        if (offeringToDelete == null) {
            throw new RuntimeException("Không tìm thấy lớp học phần để xóa.");
        }
        Long lmsId = offeringToDelete.getLms_Offering_Id();

        if (lmsId != null) {
            // GỌI API ĐỂ XÓA BẢN GHI BÊN LMS TRƯỚC
            boolean isDeletedOnLms = syncServiceOffering.deleteLmsOffering(lmsId);
            if (!isDeletedOnLms) {
                throw new RuntimeException("Xóa đồng bộ sang LMS thất bại!");
            }
        }
        class_OfferingDAO.delete(id);
    }
}
