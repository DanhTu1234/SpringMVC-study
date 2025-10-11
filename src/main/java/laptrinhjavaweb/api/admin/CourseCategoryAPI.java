package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.CategoryCourseModel;
import laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "classCourseCategoryAPIOfAdmin")
@CrossOrigin("http://127.0.0.1:5500")
public class CourseCategoryAPI {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/api/category")
    public ResponseEntity<List<CategoryCourseModel>> getAllCourses() {
        List<CategoryCourseModel> category = categoryService.findAll();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<CategoryCourseModel> getOneCategories(@PathVariable Long id) {
        CategoryCourseModel category = categoryService.findOne(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/api/category")
    public ResponseEntity<CategoryCourseModel> createCategory(@RequestBody CategoryCourseModel categoryCourseModel) {
        CategoryCourseModel saveCategory = categoryService.createSyncCategory(categoryCourseModel);

        return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
    }

    @PutMapping("/api/category/{id}")
    public ResponseEntity<CategoryCourseModel> updateCategory(@RequestBody CategoryCourseModel categoryCourseModel, @PathVariable Long id) {
        categoryCourseModel.setId(id);

        CategoryCourseModel updatedCategory = categoryService.updateSyncCategory(categoryCourseModel);

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteSyncCategory(id);
        return ResponseEntity.ok().build();
    }
}
