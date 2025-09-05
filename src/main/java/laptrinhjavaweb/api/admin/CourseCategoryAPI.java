package laptrinhjavaweb.api.admin;

import laptrinhjavaweb.model.CategoryCourseModel;
import laptrinhjavaweb.model.CourseModel;
import laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "classCourseCategoryAPIOfAdmin")
public class CourseCategoryAPI {
    @Autowired
    private ICategoryService categoryService;

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
