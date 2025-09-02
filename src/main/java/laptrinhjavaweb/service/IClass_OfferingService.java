package laptrinhjavaweb.service;

import laptrinhjavaweb.dto.Class_OfferingDTO;
import laptrinhjavaweb.model.Class_OfferingModel;

import java.util.List;

public interface IClass_OfferingService {
    List<Class_OfferingModel> findAll();
    Class_OfferingModel createAndSyncOffering(Class_OfferingModel offeringModel);
    Class_OfferingModel updateAndSyncOffering(Class_OfferingModel offeringModel);
    void deleteAndSyncOffering(long id);
    Class_OfferingModel insert(Class_OfferingModel class_OfferingModel);
    Class_OfferingModel update(Class_OfferingModel updateClass_Offering);
    void delete(long id);
    Class_OfferingModel findOne(Long id);
    Class_OfferingDTO findDetailById(Long id);
}
