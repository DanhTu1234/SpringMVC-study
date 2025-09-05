package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.DepartmentModel;
import laptrinhjavaweb.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "departmentAPIOfAdmin")
public class DepartmentAPI {

    @Autowired
    private IDepartmentService departmentService;

    @PostMapping("/api/department")
    public ResponseEntity<DepartmentModel> createDepartment(@RequestBody DepartmentModel departmentModel) {
        // Lưu vào CSDL của LCMS
        DepartmentModel save = departmentService.insert(departmentModel);

        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @PutMapping("/api/department/{id}")
    public ResponseEntity<DepartmentModel> updateDepartment(@PathVariable Long id, @RequestBody DepartmentModel departmentModel) {
        // Gán ID từ URL vào courseModel
        departmentModel.setId(id);

        DepartmentModel updatedDepartment = departmentService.update(departmentModel);

        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/api/department/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
