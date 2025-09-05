package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.client.SyncServiceOffering;
import laptrinhjavaweb.model.Class_OfferingModel;
import laptrinhjavaweb.service.IClass_OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "classOfferingAPIOfAdmin")
public class Class_OfferingAPI {
    @Autowired
    private IClass_OfferingService class_OfferingService;

    @Autowired
    private SyncServiceOffering syncServiceOffering;

    @PostMapping("/api/offering")
    public ResponseEntity<Class_OfferingModel> createClassOffering(@RequestBody Class_OfferingModel class_offeringModel) {
        Class_OfferingModel savedOffering = class_OfferingService.createAndSyncOffering(class_offeringModel);

        return new ResponseEntity<>(savedOffering, HttpStatus.CREATED);
    }

    @PutMapping("/api/offering/{id}")
    public ResponseEntity<Class_OfferingModel> updateClassOffering(@PathVariable Long id, @RequestBody Class_OfferingModel class_offeringModel) {
        // Gán ID từ URL vào class_offeringModel
        class_offeringModel.setId(id);

        // Gọi một phương thức duy nhất từ service để xử lý toàn bộ logic
        Class_OfferingModel updatedOffering = class_OfferingService.updateAndSyncOffering(class_offeringModel);

        // Trả về đối tượng đã cập nhật thành công
        return new ResponseEntity<>(updatedOffering, HttpStatus.OK);
    }

    @DeleteMapping("/api/offering/{id}")
    public ResponseEntity<String> deleteClassOffering(@PathVariable Long id) {
        class_OfferingService.deleteAndSyncOffering(id);
        return ResponseEntity.ok().build();
    }
}
